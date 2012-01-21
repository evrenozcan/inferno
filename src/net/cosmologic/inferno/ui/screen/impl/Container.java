/*
 * 
 */
package net.cosmologic.inferno.ui.screen.impl;

import net.cosmologic.inferno.IContext;
import net.cosmologic.inferno.ui.graphics.IBitmapWrapper;
import net.cosmologic.inferno.ui.graphics.IBitmapWrapper.BitmapFormat;
import net.cosmologic.inferno.ui.graphics.IGraphics;
import net.cosmologic.inferno.ui.screen.IContainer;

/**
 * The Class Container.
 * 
 * @author Evren Ozcan
 */
public abstract class Container extends Component implements IContainer
{
    
    /** The context. */
    private final IContext context;
    
    /** The container local. */
    protected IGraphics containerLocal;

    /**
     * Instantiates a new container.
     *
     * @param context the context
     * @param id the id
     * @param bounds the bounds
     * @param transparent the transparent
     */
    protected Container(final IContext context, final String id, final Bounds bounds, final boolean transparent)
    {
        super(id, bounds);
        this.context = context;
        if (transparent)
        {
            containerLocal = createGraphics(context, bounds.width, bounds.height, BitmapFormat.ARGB8888);
        }
        else
        {
            containerLocal = createGraphics(context, bounds.width, bounds.height, BitmapFormat.RGB565);
        }
    }

    /**
     * Creates the graphics.
     *
     * @param context the context
     * @param width the width
     * @param height the height
     * @param format the format
     * @return the i graphics
     */
    protected abstract IGraphics createGraphics(IContext context, int width, int height, BitmapFormat format);

    /**{@inheritDoc}*/
    @Override
    public IBitmapWrapper getBitmap()
    {
        return containerLocal.getFrameBuffer();
    }

    /**{@inheritDoc}*/
    @Override
    public void dispose()
    {
        getBitmap().dispose();
    }

    /**{@inheritDoc}*/
    @Override
    public IContext getContext()
    {
        return context;
    }

    /**{@inheritDoc}*/
    @Override
    public IGraphics getGraphics()
    {
        return containerLocal;
    }

    /**{@inheritDoc}*/
    @Override
    public int getWidth()
    {
        return bounds.width;
    }

    /**{@inheritDoc}*/
    @Override
    public int getHeight()
    {
        return bounds.height;
    }

    /**{@inheritDoc}*/
    @Override
    public boolean onInterrupt()
    {
        return false;
    }
}
