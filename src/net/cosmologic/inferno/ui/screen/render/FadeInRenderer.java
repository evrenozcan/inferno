/*
 * 
 */
package net.cosmologic.inferno.ui.screen.render;

import net.cosmologic.inferno.ui.graphics.IGraphics;
import net.cosmologic.inferno.ui.screen.DeltaAttributes;
import net.cosmologic.inferno.ui.screen.IComponent;

/**
 * The Class FadeInRenderer.
 * 
 * @author Evren Ozcan
 */
public class FadeInRenderer extends GenericDeltaProcessor
{

    /** The Constant INSTANCE. */
    public static final FadeInRenderer INSTANCE = new FadeInRenderer();

    /**{@inheritDoc}*/
    @Override
    public void render(final IGraphics graphics, final IComponent component, final float delta)
    {
        graphics.drawComponent(component, component.getAttributes().alpha);
    }

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
            attributes.alpha = 0;
            listener.onFirstEnter(component);
        }

        component.setVisible(false);

        if (attributes.delay > 0)
        {
            attributes.delay -= delta;
            return;
        }

        component.setVisible(true);

        if (attributes.alpha == 255)
        {
            attributes.paused = true;
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
            attributes.alpha += attributes.speed;
            if (attributes.alpha > 255)
            {
                attributes.alpha = 255;
            }

            if (attributes.alpha == 255)
            {
                if (attributes.repeatCount-- > 0)
                {
                    attributes.alpha = 0;
                    listener.onRepeat(component);
                }
                else
                {
                    overrideListener(component, null);
                    listener.onEnd(component);
                }
            }
        }
    }
}
