/*
 * 
 */
package net.cosmologic.inferno.ui.screen;

/**
 * The Interface ILayer.
 * 
 * @author Evren Ozcan
 */
public interface ILayer extends IContainer
{
    
    /**
     * Adds the component.
     *
     * @param component the component
     * @param asynch the asynch
     */
    void addComponent(final IComponent component, boolean asynch);

    /**
     * Removes the component.
     *
     * @param component the component
     */
    void removeComponent(final IComponent component);

    /**
     * Gets the action navigator.
     *
     * @return the action navigator
     */
    INavigator getActionNavigator();
}