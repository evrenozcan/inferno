/*
 * 
 */
package net.cosmologic.inferno.android;

import net.cosmologic.agelaos.ui.loader.AssetLoaderProcess;
import net.cosmologic.inferno.IContext;
import net.cosmologic.inferno.android.ui.graphics.AndroidGraphics;
import net.cosmologic.inferno.android.util.InfernoLogger;
import net.cosmologic.inferno.ui.graphics.IBitmapWrapper.BitmapFormat;
import net.cosmologic.inferno.ui.graphics.IGraphics;
import net.cosmologic.inferno.ui.screen.impl.GamePlatform;
import net.cosmologic.inferno.util.IOUtils;

/**
 * The Class AndroidGamePlatform.
 * 
 * 
 * @author Evren Ozcan
 */
public abstract class AndroidGamePlatform extends GamePlatform
{

    // XXX;
    /** The fps calc. */
    float fpsCalc;
    
    /** The fps. */
    int fps = 0;
    
    /** The fps delay. */
    int fpsDelay = 2;
    
    /** The finalfps. */
    int finalfps = 0;

    /**
     * Instantiates a new android game platform.
     *
     * @param context the context
     * @param id the id
     * @param bounds the bounds
     * @param transparent the transparent
     */
    protected AndroidGamePlatform(final InfernoAndroidContext context, final String id, final Bounds bounds,
            final boolean transparent)
    {
        super(context, id, bounds, transparent);
        context.setPlatform(this);
    }

    /**
     * Instantiates a new android game platform.
     *
     * @param context the context
     * @param id the id
     * @param bounds the bounds
     */
    protected AndroidGamePlatform(final InfernoAndroidContext context, final String id, final Bounds bounds)
    {
        this(context, id, bounds, false);
    }

    /**{@inheritDoc}*/
    @Override
    public void update(final float delta)
    {
        super.update(delta);
        if (InfernoLogger.isDebugEnabled())
        {
            fpsCalc += delta;
            fps++;
            if (fpsCalc >= 1)
            {
                fpsDelay--;
                if (fpsDelay == 0)
                {
                    fpsDelay = 2;
                    finalfps = fps;
                }
                fpsCalc = 0;
                fps = 0;
            }
            InfernoLogger.info("InfernoAgelaosFPS", String.valueOf((int) (delta * 1000)) + " : " + finalfps);
        }
    }

    /**{@inheritDoc}*/
    @Override
    protected final IGraphics createGraphics(final IContext context, final int width, final int height,
            final BitmapFormat format)
    {
        return new AndroidGraphics(context, "game-platform", width, height, format);
    }

    /**{@inheritDoc}*/
    @Override
    public void end()
    {
        ((InfernoAndroidContext) getContext()).getActivity().finish();
    }

    /**{@inheritDoc}*/
    @Override
    public boolean onInterrupt()
    {
        return getDisplay().onInterrupt();
    }

    /**{@inheritDoc}*/
    @Override
    public void dispose()
    {
        IOUtils.saveContextToFile(getContext(), AssetLoaderProcess.CONFIG_FILE, null);
        super.dispose();
    }
}
