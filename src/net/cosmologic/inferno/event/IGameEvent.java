/*
 * 
 */
package net.cosmologic.inferno.event;

import net.cosmologic.inferno.event.IGameEvent.EventType.Action;

/**
 * The Interface IGameEvent.
 * 
 * @author Evren Ozcan
 */
public interface IGameEvent
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
    Object getData();

    /**
     * Gets the description.
     *
     * @return the description
     */
    String getDescription();

    /**
     * Gets the type.
     *
     * @return the type
     */
    EventType getType();

    /**
     * Gets the action.
     *
     * @return the action
     */
    Action getAction();

    /**
     * The Enum EventType.
     */
    public enum EventType
    {
        
        /** The TOUCH. */
        TOUCH, 
 /** The KEYPRESS. */
 KEYPRESS, 
 /** The INTERNAL. */
 INTERNAL;
        
        /**
         * The Enum Action.
         */
        public enum Action
        {
            
            /** The KE y_ down. */
            KEY_DOWN, 
 /** The KE y_ up. */
 KEY_UP, 
 /** The TOUC h_ down. */
 TOUCH_DOWN, 
 /** The TOUC h_ up. */
 TOUCH_UP, 
 /** The TOUC h_ dragged. */
 TOUCH_DRAGGED, 
 /** The SWITC h_ display. */
 SWITCH_DISPLAY, 
 /** The SWITC h_ player. */
 SWITCH_PLAYER;
        }
    }
}