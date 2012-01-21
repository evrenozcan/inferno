/*
 * 
 */
package net.cosmologic.inferno.ui.screen;

import net.cosmologic.inferno.ui.graphics.IBitmapWrapper;

/**
 * The Interface ICheckBoxDrawable.
 */
public interface ICheckBoxDrawable extends ITextDrawable
{
    
    /**
     * Gets the rollover icon.
     *
     * @return the rollover icon
     */
    public IBitmapWrapper getRolloverIcon();

    /**
     * Gets the context key.
     *
     * @return the context key
     */
    String getContextKey();

    /**
     * Checks if is checked.
     *
     * @return true, if is checked
     */
    boolean isChecked();
}
