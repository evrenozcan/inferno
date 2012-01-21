/*
 * 
 */
package net.cosmologic.inferno.ui.screen;

import net.cosmologic.inferno.event.IActionListener;

/**
 * The Interface IActionDrawable.
 * 
 * @author Evren Ozcan
 */
public interface IActionDrawable
{
    
    /**
     * Checks if is enabled.
     *
     * @return true, if is enabled
     */
    boolean isEnabled();

    /**
     * Sets the enabled.
     *
     * @param enabled the new enabled
     */
    void setEnabled(boolean enabled);

    /**
     * Sets the action listener.
     *
     * @param listener the new action listener
     */
    void setActionListener(IActionListener listener);
}
