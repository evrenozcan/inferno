/*
 * 
 */
package net.cosmologic.inferno.ui.screen.render;

import net.cosmologic.inferno.ui.screen.IComponent;

/**
 * The listener interface for receiving IDeltaProcess events.
 * The class that is interested in processing a IDeltaProcess
 * event implements this interface, and the object created
 * with that class is registered with a component using the
 * component's <code>addIDeltaProcessListener<code> method. When
 * the IDeltaProcess event occurs, that object's appropriate
 * method is invoked.
 *
 * @see IDeltaProcessEvent
 * 
 * @author Evren Ozcan
 */
public interface IDeltaProcessListener
{
    
    /**
     * On first enter.
     *
     * @param component the component
     */
    void onFirstEnter(IComponent component);

    /**
     * On start.
     *
     * @param component the component
     */
    void onStart(IComponent component);

    /**
     * On end.
     *
     * @param component the component
     */
    void onEnd(IComponent component);

    /**
     * On repeat.
     *
     * @param component the component
     */
    void onRepeat(IComponent component);
}
