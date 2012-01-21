/*
 * 
 */
package net.cosmologic.inferno.android.ui.graphics;

import net.cosmologic.inferno.ui.graphics.IBitmapWrapper;
import android.graphics.Bitmap;

/**
 * The Class AndroidBitmapWrapper.
 * 
 * @author Evren Ozcan
 */
public class AndroidBitmapWrapper implements IBitmapWrapper
{
    
    /** The raw. */
    private final Bitmap raw;
    
    /** The format. */
    private final BitmapFormat format;
    
    /** The id. */
    private final String id;
    
    /** The shared. */
    private boolean shared = true;

    /**
     * Instantiates a new android bitmap wrapper.
     *
     * @param id the id
     * @param bitmap the bitmap
     * @param format the format
     */
    public AndroidBitmapWrapper(final String id, final Bitmap bitmap, final BitmapFormat format)
    {
        this.id = id;
        this.raw = bitmap;
        this.format = format;
    }

    /**{@inheritDoc}*/
    @Override
    public int getWidth()
    {
        return raw.getWidth();
    }

    /**{@inheritDoc}*/
    @Override
    public int getHeight()
    {
        return raw.getHeight();
    }

    /**{@inheritDoc}*/
    @Override
    public BitmapFormat getFormat()
    {
        return format;
    }

    /**{@inheritDoc}*/
    @Override
    public void dispose()
    {
        raw.recycle();
    }

    /**{@inheritDoc}*/
    @Override
    public Bitmap getRaw()
    {
        return raw;
    }

    /**{@inheritDoc}*/
    @Override
    public String getId()
    {
        return id;
    }
    
    /**{@inheritDoc}*/
    @Override
    public boolean isShared()
    {
        return shared;
    }

    /**{@inheritDoc}*/
    @Override
    public void setShared(final boolean shared)
    {
        this.shared = shared;
    }

}
