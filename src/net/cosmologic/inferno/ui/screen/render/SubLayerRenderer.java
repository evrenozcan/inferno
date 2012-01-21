/*
 * 
 */
package net.cosmologic.inferno.ui.screen.render;

import net.cosmologic.inferno.ui.screen.DeltaAttributes;
import net.cosmologic.inferno.ui.screen.IComponent;
import net.cosmologic.inferno.ui.screen.IComponent.Bounds;
import net.cosmologic.inferno.ui.screen.ILayer;

/**
 * The Class SubLayerRenderer.
 * 
 * @author Evren Ozcan
 */
public class SubLayerRenderer extends GenericDeltaProcessor
{

    /** The Constant INSTANCE. */
    public static final SubLayerRenderer INSTANCE = new SubLayerRenderer();

    /**{@inheritDoc}*/
    @Override
    public void update(final IComponent component, final float delta)
    {
        final DeltaAttributes attributes = component.getAttributes();
        final Bounds bounds = component.getBounds();
        if ((null == attributes) || attributes.paused)
        {
            return;
        }

        final IDeltaProcessListener listener = getListener(component);

        if (!attributes.updated)
        {
            attributes.updated = true;
            bounds.scale = 0f;
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
        else
        {
            attributes.elapsed = 0;
        }

        if (attributes.active)
        {
            if (bounds.scale < 1f)
            {
                bounds.scale = bounds.scale + 0.2f;
            }
            if (bounds.scale >= 1f)
            {
                bounds.scale = 1f;
                listener.onEnd(component);
                attributes.paused = true;
            }
        }
        else
        {
            if (bounds.scale > 0)
            {
                bounds.scale = bounds.scale - 0.333f;
            }
            if (bounds.scale <= 0f)
            {
                ((ILayer) component.getParent()).removeComponent(component);
                bounds.scale = 0f;
                attributes.active = true;
                if (null != listener)
                {
                    listener.onStart(component);
                    overrideListener(component, null);
                }
            }
        }
    }
}
