/*
 * 
 */
package net.cosmologic.inferno.ui.screen.impl;

import net.cosmologic.inferno.sound.ISoundEffect;
import net.cosmologic.inferno.ui.graphics.IBitmapWrapper;
import net.cosmologic.inferno.ui.screen.IBitmapDrawable;
import net.cosmologic.inferno.ui.screen.IContainer;

/**
 * The Class BitmapActionItem.
 * 
 * @author Evren Ozcan
 */
public class BitmapActionItem extends ActionItem implements IBitmapDrawable
{
    
    /** The bitmap. */
    private final IBitmapWrapper bitmap;

    /**
     * Instantiates a new bitmap action item.
     *
     * @param parent the parent
     * @param id the id
     * @param bitmap the bitmap
     * @param onClick the on click
     */
    public BitmapActionItem(final IContainer parent, final String id, final IBitmapWrapper bitmap,
            final ISoundEffect onClick)
    {
        super(parent, id, onClick);
        this.bitmap = bitmap;
    }

    /**
     * Instantiates a new bitmap action item.
     *
     * @param parent the parent
     * @param id the id
     * @param bitmap the bitmap
     * @param onClick the on click
     * @param x the x
     * @param y the y
     * @param w the w
     * @param h the h
     * @param srcX the src x
     * @param srcY the src y
     */
    public BitmapActionItem(final IContainer parent, final String id, final IBitmapWrapper bitmap,
            final ISoundEffect onClick, final int x, final int y, final int w, final int h, final int srcX,
            final int srcY)
    {
        super(parent, id, onClick, x, y, w, h, srcX, srcY);
        this.bitmap = bitmap;
    }

    /**
     * Instantiates a new bitmap action item.
     *
     * @param parent the parent
     * @param id the id
     * @param bitmap the bitmap
     * @param onClick the on click
     * @param x the x
     * @param y the y
     */
    public BitmapActionItem(final IContainer parent, final String id, final IBitmapWrapper bitmap,
            final ISoundEffect onClick, final int x, final int y)
    {
        super(parent, id, onClick, x, y, bitmap.getWidth(), bitmap.getHeight(), 0, 0);
        this.bitmap = bitmap;
    }

    /**{@inheritDoc}*/
    @Override
    public IBitmapWrapper getBitmap()
    {
        return bitmap;
    }
}
