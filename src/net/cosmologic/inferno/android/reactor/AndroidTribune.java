/*
 * 
 */
package net.cosmologic.inferno.android.reactor;

import net.cosmologic.inferno.android.InfernoAndroidContext;
import net.cosmologic.inferno.reactor.AbstractTribune;
import net.cosmologic.inferno.sound.ISoundEffect;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.SurfaceHolder;

/**
 * The Class AndroidTribune.
 * 
 * @author Evren Ozcan
 */
public class AndroidTribune extends AbstractTribune
{

    /** The target. */
    private Rect target;
    
    /** The holder. */
    private final SurfaceHolder holder;
    
    /** The Constant TAG. */
    public static final String TAG = "Inferno-AndroidTribune";
    
    /** The virtual frame buffer. */
    private final Bitmap virtualFrameBuffer;

    /**
     * Instantiates a new android tribune.
     *
     * @param context the context
     */
    public AndroidTribune(final InfernoAndroidContext context)
    {
        super(context);
        this.virtualFrameBuffer = (Bitmap) context.getGamePlatform().getBitmap().getRaw();
        this.holder = context.getActivity().getView().getHolder();
        holder.addCallback(context.getActivity().getView());
    }

    /**{@inheritDoc}*/
    @Override
    protected void preStart()
    {
        target = new Rect();
    }

    /** The canvas. */
    private Canvas canvas = null;

    /**{@inheritDoc}*/
    @Override
    protected void onNextFrame(final float delta)
    {
        if (!holder.getSurface().isValid())
        {
            return;
        }
        // Logger.debug(TAG, "Delta time : " + delta * 1000);
        context.getGamePlatform().refreshFrame(delta);
        try
        {
            canvas = holder.lockCanvas();
            canvas.getClipBounds(target);
            canvas.drawBitmap(virtualFrameBuffer, null, target, null);
        }
        finally
        {
            holder.unlockCanvasAndPost(canvas);
        }
    }

    /**{@inheritDoc}*/
    @Override
    protected void onLoopEnd()
    {
        super.onLoopEnd();
        if (null != canvas)
        {
            try
            {
                // holder.unlockCanvasAndPost(canvas);
            }
            catch (final Exception e)
            {

            }
        }
    }

    /**{@inheritDoc}*/
    @Override
    public final void queueSoundEffect(final ISoundEffect effect)
    {
        if (context.getAudio().isSoundEnabled())
        {
            super.queueSoundEffect(effect);
        }
    }
}
