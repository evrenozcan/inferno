/*
 * 
 */
package net.cosmologic.inferno.event;

import net.cosmologic.inferno.ui.screen.IComponent;

/**
 * The listener interface for receiving IAction events.
 * The class that is interested in processing a IAction
 * event implements this interface, and the object created
 * with that class is registered with a component using the
 * component's <code>addIActionListener<code> method. When
 * the IAction event occurs, that object's appropriate
 * method is invoked.
 *
 * @see IActionEvent
 * 
 * @author Evren Ozcan
 */
public interface IActionListener
{
    
    /**
     * On action.
     *
     * @param component the component
     */
    void onAction(IComponent component);
}
