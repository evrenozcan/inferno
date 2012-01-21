/*
 * 
 */
package net.cosmologic.inferno.ui.screen.render;

import net.cosmologic.inferno.ui.graphics.IGraphics;
import net.cosmologic.inferno.ui.screen.DeltaAttributes;
import net.cosmologic.inferno.ui.screen.IComponent;
import net.cosmologic.inferno.ui.screen.IComponent.Bounds;

/**
 * The Class Slider.
 * 
 * @author Evren Ozcan
 */
public class Slider extends GenericDeltaProcessor
{

    /** The Constant INSTANCE. */
    public static final Slider INSTANCE = new Slider();

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

        if (attributes.delay > 0)
        {
            attributes.delay -= delta;
            return;
        }

        if (!attributes.started)
        {
            attributes.started = true;
            listener.onStart(component);
        }
        final Bounds bounds = component.getBounds();
        final Bounds parentBounds = component.getParent().getBounds();
        if (((bounds.x + bounds.width < 0) || (bounds.x > parentBounds.width))
                || ((bounds.y + bounds.height < 0) || (bounds.y > parentBounds.height)))
        {
            attributes.paused = true;
            component.setVisible(false);
            listener.onEnd(component);
            reset();
        }
        else
        {
            if (attributes.elapsed < attributes.precision)
            {
                attributes.elapsed += delta;
                return;
            }
            else
            {
                attributes.elapsed = 0;
            }
            if (attributes.angle != 0)
            {
                final double radian = Math.toRadians(attributes.angle);
                final float x = (float) Math.sin(radian);
                final float y = (float) Math.cos(radian);
                bounds.x += (int) (attributes.speed * x);
                bounds.y += (int) (attributes.speed * y);
            }
        }
    }

    /**{@inheritDoc}*/
    @Override
    public void render(final IGraphics graphics, final IComponent component, final float delta)
    {
        final DeltaAttributes attributes = component.getAttributes();
        final IDeltaProcessListener listener = getListener(component);
        if ((null != attributes) && !attributes.updated)
        {
            attributes.updated = true;
            listener.onFirstEnter(component);
        }

        super.render(graphics, component, delta);
    }
}
