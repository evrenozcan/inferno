/*
 * 
 */
package net.cosmologic.inferno.reactor;

import net.cosmologic.inferno.event.IEvent;

/**
 * The listener interface for receiving IReactorProcess events.
 * The class that is interested in processing a IReactorProcess
 * event implements this interface, and the object created
 * with that class is registered with a component using the
 * component's <code>addIReactorProcessListener<code> method. When
 * the IReactorProcess event occurs, that object's appropriate
 * method is invoked.
 *
 * @see IReactorProcessEvent
 * 
 * 
 * @author Evren Ozcan
 */
public interface IReactorProcessListener
{
    
    /**
     * On process end.
     *
     * @param task the task
     */
    void onProcessEnd(IReactorProcess task);

    /**
     * On progress.
     *
     * @param event the event
     */
    void onProgress(IEvent<Integer> event);
}
