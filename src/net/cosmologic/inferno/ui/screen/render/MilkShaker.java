/*
 * 
 */
package net.cosmologic.inferno.ui.screen.render;

import net.cosmologic.inferno.ui.screen.DeltaAttributes;
import net.cosmologic.inferno.ui.screen.IComponent;

/**
 * The Class MilkShaker.
 * 
 * @author Evren Ozcan
 */
public class MilkShaker extends GenericDeltaProcessor
{

    /** The Constant INSTANCE. */
    public static final MilkShaker INSTANCE = new MilkShaker();

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
            attributes.delay -= delta;
            if ((Math.random() * 3) % 2 == 0)
            {
                component.getBounds().scale = 0.98f;
            }
            else
            {
                component.getBounds().scale = 0.97f;
            }
        }
        else
        {
            attributes.paused = true;
            component.getBounds().scale = 1f;
            reset();
            listener.onEnd(component);
        }
    }
}
