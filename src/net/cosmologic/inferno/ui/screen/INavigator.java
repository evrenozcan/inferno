/*
 * 
 */
package net.cosmologic.inferno.ui.screen;

/**
 * The Interface INavigator.
 * 
 * @author Evren Ozcan
 */
public interface INavigator
{
    
    /**
     * Sets the current action.
     *
     * @param component the new current action
     */
    void setCurrentAction(IComponent component);

    /**
     * Gets the current action.
     *
     * @return the current action
     */
    IComponent getCurrentAction();

    /**
     * Gets the action.
     *
     * @param eventX the event x
     * @param eventY the event y
     * @return the action
     */
    IComponent getAction(int eventX, int eventY);

    /**
     * Navigate down.
     */
    void navigateDown();

    /**
     * Navigate up.
     */
    void navigateUp();

    /**
     * Reset.
     */
    void reset();
}
