/*
 * 
 */
package net.cosmologic.inferno.animation;

import java.util.ArrayList;
import java.util.List;

import net.cosmologic.inferno.ui.screen.IContainer;

/**
 * The Class InfernoAnimation.
 * 
 * @author Evren Ozcan
 */
public class InfernoAnimation
{
    
    /** The frames. */
    private final List<InfernoFrame> frames = new ArrayList<InfernoFrame>();
    
    /** The parent. */
    private final IContainer parent;
    
    /** The frame index. */
    int frameIndex = 0;
    
    /** The repeat. */
    int repeat = 0;

    /**
     * Instantiates a new inferno animation.
     *
     * @param parent the parent
     * @param repeat the repeat
     */
    public InfernoAnimation(final IContainer parent, final int repeat)
    {
        this.parent = parent;
        this.repeat = repeat;
    }

    /**
     * Play.
     *
     * @param delta the delta
     */
    public void play(final float delta)
    {
        update(delta);
        InfernoFrame frame = getCurrentFrame();
        if ((null == frame) && (frameIndex > 0))
        {
            if (repeat > 0)
            {
                reset();
                repeat--;
                frame = getCurrentFrame();
            }
        }
        if (null != frame)
        {
            parent.getGraphics().drawAnitmation(this);
        }
    }

    /**
     * Reset.
     */
    public void reset()
    {
        frameIndex = 0;
        for (final InfernoFrame frame : frames)
        {
            frame.reset();
        }
    }

    /**
     * Update.
     *
     * @param delta the delta
     */
    public void update(final float delta)
    {
        if (frames.isEmpty())
        {
            return;
        }
        final InfernoFrame frame = getCurrentFrame();
        if (null == frame)
        {
            return;
        }
        frame.update(delta);
        if (frame.isCompleted())
        {
            frameIndex++;
        }
    }

    /**
     * Adds the frame.
     *
     * @param frame the frame
     */
    public void addFrame(final InfernoFrame frame)
    {
        frames.add(frame);
    }

    /**
     * Gets the current frame.
     *
     * @return the current frame
     */
    public InfernoFrame getCurrentFrame()
    {
        if (frames.isEmpty() || (frameIndex == frames.size()))
        {
            return null;
        }
        return frames.get(frameIndex);
    }
}
