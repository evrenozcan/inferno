/*
 * 
 */
package net.cosmologic.inferno.ui.screen;

import net.cosmologic.inferno.ui.graphics.IBitmapWrapper;

/**
 * The Interface IBitmapDrawable.
 * 
 * @author Evren Ozcan
 */
public interface IBitmapDrawable extends IComponent
{
    
    /**
     * Gets the bitmap.
     *
     * @return the bitmap
     */
    IBitmapWrapper getBitmap();
}
