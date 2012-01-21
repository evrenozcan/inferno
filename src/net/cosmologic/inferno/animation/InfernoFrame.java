/*
 * 
 */
package net.cosmologic.inferno.animation;

import net.cosmologic.inferno.ui.graphics.IBitmapWrapper;

/**
 * The Class InfernoFrame.
 * 
 * @author Evren Ozcan
 */
public class InfernoFrame
{
    
    /** The bitmap. */
    private final IBitmapWrapper bitmap;
    
    /** The height. */
    public int x, y, srcX, srcY, width, height;
    
    /** The duration. */
    private float duration;
    
    /** The scale. */
    public float scale = 1f;
    
    /** The default duration. */
    private final float defaultDuration;

    /**
     * Instantiates a new inferno frame.
     *
     * @param wrapper the wrapper
     * @param x the x
     * @param y the y
     * @param srcX the src x
     * @param srcY the src y
     * @param w the w
     * @param h the h
     * @param duration the duration
     */
    public InfernoFrame(final IBitmapWrapper wrapper, final int x, final int y, final int srcX, final int srcY,
            final int w, final int h, final float duration)
    {
        this.bitmap = wrapper;
        this.x = x;
        this.y = y;
        this.width = w;
        this.height = h;
        this.srcX = srcX;
        this.srcY = srcY;
        this.duration = duration;
        defaultDuration = duration;
    }

    /**
     * Gets the bitmap.
     *
     * @return the bitmap
     */
    public IBitmapWrapper getBitmap()
    {
        return bitmap;
    }

    /**
     * Reset.
     */
    public void reset()
    {
        duration = defaultDuration;
        scale = 1f;
    }

    /**
     * Update.
     *
     * @param delta the delta
     */
    public void update(final float delta)
    {
        duration -= delta;
    }

    /**
     * Checks if is completed.
     *
     * @return true, if is completed
     */
    public boolean isCompleted()
    {
        return duration <= 0;
    }
}
