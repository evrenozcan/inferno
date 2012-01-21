/*
 * 
 */
package net.cosmologic.inferno.ui.graphics;

import net.cosmologic.inferno.IContext;
import net.cosmologic.inferno.ui.graphics.IBitmapWrapper.BitmapFormat;

/**
 * The Class AbstractGraphics.
 * 
 * @author Evren Ozcan
 */
public abstract class AbstractGraphics implements IGraphics
{
    
    /** The context. */
    protected IContext context;
    
    /** The virtual frame buffer. */
    private final IBitmapWrapper virtualFrameBuffer;
    
    /** The id. */
    private final String id;

    /**
     * Instantiates a new abstract graphics.
     *
     * @param context the context
     * @param id the id
     * @param width the width
     * @param height the height
     * @param format the format
     */
    protected AbstractGraphics(final IContext context, final String id, final int width, final int height,
            final BitmapFormat format)
    {
        this.id = id;
        this.context = context;
        virtualFrameBuffer = context.getFileIO().createBitmapWrapper(id, width, height, format);
    }

    /**
     * Gets the id.
     *
     * @return the id
     */
    public String getId()
    {
        return id;
    }

    /**{@inheritDoc}*/
    @Override
    public int getWidth()
    {
        return virtualFrameBuffer.getWidth();
    }

    /**{@inheritDoc}*/
    @Override
    public int getHeight()
    {
        return virtualFrameBuffer.getHeight();
    }

    /**{@inheritDoc}*/
    @Override
    public IBitmapWrapper getFrameBuffer()
    {
        return virtualFrameBuffer;
    }

    /**{@inheritDoc}*/
    @Override
    public String toString()
    {
        return "graphics:" + id + " w:" + virtualFrameBuffer.getWidth() + " h:" + virtualFrameBuffer.getHeight();
    }
}
