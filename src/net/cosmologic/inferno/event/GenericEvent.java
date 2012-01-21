/*
 * 
 */
package net.cosmologic.inferno.event;

import net.cosmologic.inferno.event.IGameEvent.EventType.Action;
import net.cosmologic.inferno.util.StringUtils;

/**
 * The Class GenericEvent.
 * 
 * @author Evren Ozcan
 */
public class GenericEvent implements IGameEvent
{
    
    /** The source. */
    private final Object source;
    
    /** The data. */
    private final Object data;
    
    /** The description. */
    private final String description;
    
    /** The type. */
    public EventType type;
    
    /** The action. */
    public Action action;

    /**
     * Instantiates a new generic event.
     *
     * @param source the source
     * @param data the data
     * @param type the type
     */
    public GenericEvent(final Object source, final Object data, final EventType type)
    {
        this(source, data, type, StringUtils.EMPTY);
    }

    /**
     * Instantiates a new generic event.
     *
     * @param source the source
     * @param data the data
     * @param type the type
     * @param description the description
     */
    public GenericEvent(final Object source, final Object data, final EventType type, final String description)
    {
        this.source = source;
        this.data = data;
        this.type = type;
        this.description = description;
    }

    /**{@inheritDoc}*/
    @Override
    public Object getSource()
    {
        return source;
    }

    /**{@inheritDoc}*/
    @Override
    public Object getData()
    {
        return data;
    }

    /**{@inheritDoc}*/
    @Override
    public String getDescription()
    {
        return description;
    }

    /**{@inheritDoc}*/
    @Override
    public EventType getType()
    {
        return type;
    }

    /**{@inheritDoc}*/
    @Override
    public Action getAction()
    {
        return action;
    }
}
