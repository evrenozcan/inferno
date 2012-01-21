/*
 * 
 */
package net.cosmologic.inferno.ui.screen.render;

import java.util.HashMap;
import java.util.Map;

import net.cosmologic.inferno.ui.graphics.IGraphics;
import net.cosmologic.inferno.ui.screen.DeltaAttributes;
import net.cosmologic.inferno.ui.screen.IComponent;

/**
 * The Class GenericDeltaProcessor.
 * 
 * @author Evren Ozcan
 */
public class GenericDeltaProcessor extends AbstractDeltaProcessListener implements IDeltaProcessor
{
    
    /** The Constant INSTANCE. */
    public static final GenericDeltaProcessor INSTANCE = new GenericDeltaProcessor();
    
    /** The listeners. */
    private Map<String, IDeltaProcessListener> listeners;

    /**{@inheritDoc}*/
    @Override
    public void update(final IComponent component, final float delta)
    {
        final DeltaAttributes attributes = component.getAttributes();
        if ((null == attributes) || attributes.paused)
        {
            return;
        }

        final IDeltaProcessListener listener = getListener(component);

        if (!attributes.updated)
        {
            attributes.updated = true;
            listener.onFirstEnter(component);
        }

        if (attributes.delay > 0)
        {
            component.setVisible(false);
            attributes.delay -= delta;
            return;
        }

        if (attributes.elapsed < attributes.precision)
        {
            attributes.elapsed += delta;
            return;
        }

        if (!attributes.started)
        {
            attributes.started = true;
            getListener(component).onStart(component);
            listener.onStart(component);
        }

        component.setVisible(!component.isVisible());

        if (attributes.repeatCount-- > 0)
        {
            attributes.elapsed = 0;
            listener.onRepeat(component);
            return;
        }
        else
        {
            component.setVisible(true);
        }

        attributes.paused = true;
        listener.onEnd(component);
        reset();
    }

    /**
     * Reset.
     */
    public void reset()
    {
        if (null != listeners)
        {
            listeners.clear();
        }
    }
    
    /**{@inheritDoc}*/
    @Override
    public void render(final IGraphics graphics, final IComponent component, final float delta)
    {
        graphics.drawComponent(component);
    }

    /**{@inheritDoc}*/
    @Override
    public void overrideListener(final IComponent component, final IDeltaProcessListener listener)
    {
        if (null == listeners)
        {
            listeners = new HashMap<String, IDeltaProcessListener>(2);
        }
        listeners.put(component.getId(), listener);
    }

    /**
     * Gets the listener.
     *
     * @param component the component
     * @return the listener
     */
    public IDeltaProcessListener getListener(final IComponent component)
    {
        if (null == listeners)
        {
            return this;
        }
        else
        {
            final IDeltaProcessListener listener = listeners.get(component.getId());
            if (null == listener)
            {
                return this;
            }
            else
            {
                return listener;
            }
        }
    }
}
