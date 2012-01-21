/*
 * 
 */
package net.cosmologic.inferno.ui.screen.impl;

import net.cosmologic.inferno.ui.graphics.IBitmapWrapper;
import net.cosmologic.inferno.ui.screen.IBitmapDrawable;

/**
 * The Class BitmapItem.
 * 
 * @author Evren Ozcan
 */
public class BitmapItem extends Component implements IBitmapDrawable
{

    /** The bitmap. */
    private final IBitmapWrapper bitmap;

    /**
     * Instantiates a new bitmap item.
     *
     * @param bitmap the bitmap
     * @param id the id
     */
    public BitmapItem(final IBitmapWrapper bitmap, final String id)
    {
        this(bitmap, id, new Bounds(0, 0, bitmap.getWidth(), bitmap.getHeight()));
    }

    /**
     * Instantiates a new bitmap item.
     *
     * @param bitmap the bitmap
     * @param id the id
     * @param x the x
     * @param y the y
     */
    public BitmapItem(final IBitmapWrapper bitmap, final String id, final int x, final int y)
    {
        this(bitmap, id, new Bounds(x, y, bitmap.getWidth(), bitmap.getHeight()));
    }

    /**
     * Instantiates a new bitmap item.
     *
     * @param bitmap the bitmap
     * @param id the id
     * @param x the x
     * @param y the y
     * @param w the w
     * @param h the h
     */
    public BitmapItem(final IBitmapWrapper bitmap, final String id, final int x, final int y, final int w, final int h)
    {
        this(bitmap, id, new Bounds(x, y, w, h));
    }

    /**
     * Instantiates a new bitmap item.
     *
     * @param bitmap the bitmap
     * @param id the id
     * @param x the x
     * @param y the y
     * @param w the w
     * @param h the h
     * @param srcX the src x
     * @param srcY the src y
     */
    public BitmapItem(final IBitmapWrapper bitmap, final String id, final int x, final int y, final int w, final int h,
            final int srcX, final int srcY)
    {
        this(bitmap, id, new Bounds(x, y, w, h, srcX, srcY));
    }

    /**
     * Instantiates a new bitmap item.
     *
     * @param bitmap the bitmap
     * @param id the id
     * @param bounds the bounds
     */
    public BitmapItem(final IBitmapWrapper bitmap, final String id, final Bounds bounds)
    {
        super(id, bounds);
        this.bitmap = bitmap;
    }

    /**{@inheritDoc}*/
    @Override
    public Layer getParent()
    {
        return (Layer) super.getParent();
    }

    /**{@inheritDoc}*/
    @Override
    public IBitmapWrapper getBitmap()
    {
        return bitmap;
    }
}
