/*
 * 
 */
package net.cosmologic.inferno.event;

/**
 * The Interface IEvent.
 *
 * @param <T> the generic type
 * 
 * @author Evren Ozcan
 */
public interface IEvent<T extends Object>
{
    
    /**
     * Gets the source.
     *
     * @return the source
     */
    Object getSource();

    /**
     * Gets the data.
     *
     * @return the data
     */
    T getData();

    /**
     * Gets the description.
     *
     * @return the description
     */
    String getDescription();
}