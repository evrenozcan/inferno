/*
 * 
 */
package net.cosmologic.inferno.ui.screen;

/**
 * The Interface IDialog.
 * 
 * @author Evren Ozcan
 */
public interface IDialog
{
    
    /**
     * On return.
     *
     * @param titleOverride the title override
     * @param success the success
     * @param timeout the timeout
     */
    void onReturn(String titleOverride, boolean success, float timeout);

    /**
     * Gets the owner.
     *
     * @return the owner
     */
    IComponent getOwner();
}
