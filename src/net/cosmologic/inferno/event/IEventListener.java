/*
 * 
 */
package net.cosmologic.inferno.event;

/**
 * The listener interface for receiving IEvent events.
 * The class that is interested in processing a IEvent
 * event implements this interface, and the object created
 * with that class is registered with a component using the
 * component's <code>addIEventListener<code> method. When
 * the IEvent event occurs, that object's appropriate
 * method is invoked.
 *
 * @param <T> the generic type
 * @see IEventEvent
 * 
 * @author Evren Ozcan
 */
public interface IEventListener<T>
{
    
    /**
     * On event.
     *
     * @param event the event
     */
    void onEvent(IEvent<T> event);

    /**
     * On end.
     */
    void onEnd();
}
