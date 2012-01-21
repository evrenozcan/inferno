/*
 * 
 */
package net.cosmologic.inferno.ui.screen.render;

import net.cosmologic.inferno.ui.graphics.IGraphics;
import net.cosmologic.inferno.ui.screen.DeltaAttributes;
import net.cosmologic.inferno.ui.screen.IComponent;

/**
 * The Class RandomDelayComponentRenderer.
 * 
 * @author Evren Ozcan
 */
public class RandomDelayComponentRenderer extends GenericDeltaProcessor
{

    /** The coords. */
    private final float[][] coords;
    
    /** The area height. */
    private final int relativeX, relativeY, areaWidth, areaHeight;

    /**
     * Instantiates a new random delay component renderer.
     *
     * @param count the count
     * @param relativeX the relative x
     * @param relativeY the relative y
     * @param areaWidth the area width
     * @param areaHeight the area height
     */
    public RandomDelayComponentRenderer(final int count, final int relativeX, final int relativeY, final int areaWidth,
            final int areaHeight)
    {
        this.relativeX = relativeX;
        this.relativeY = relativeY;
        this.areaWidth = areaWidth;
        this.areaHeight = areaHeight;
        coords = new float[count][3];
    }

    /**
     * Reset.
     *
     * @param component the component
     * @param index the index
     */
    private void reset(final IComponent component, final int index)
    {
        int x = (int) (Math.random() * areaWidth) + relativeX;
        int y = (int) (Math.random() * areaHeight) + relativeY;

        x -= (x - relativeX) % (component.getBounds().width - 1);
        y -= (y - relativeY) % (component.getBounds().height - 1);

        if (x >= relativeX + areaWidth - 1)
        {
            x -= component.getBounds().width;
        }
        if (y >= relativeY + areaHeight)
        {
            y -= component.getBounds().height;
        }

        coords[index][0] = x;
        coords[index][1] = y;
        coords[index][2] = (float) (Math.random());
    }

    /**{@inheritDoc}*/
    @Override
    public void update(final IComponent component, final float delta)
    {
        final DeltaAttributes attributes = component.getAttributes();
        if (null == attributes)
        {
            return;
        }
        final IDeltaProcessListener listener = getListener(component);
        if (!attributes.updated)
        {
            for (int i = 0; i < coords.length; i++)
            {
                reset(component, i);
            }
            attributes.updated = true;
            listener.onFirstEnter(component);
        }
    }

    /**{@inheritDoc}*/
    @Override
    public void render(final IGraphics graphics, final IComponent component, final float delta)
    {
        final DeltaAttributes attributes = component.getAttributes();
        for (int i = 0; i < coords.length; i++)
        {
            final int x = (int) coords[i][0];
            final int y = (int) coords[i][1];
            if (attributes.paused || !component.getParent().getContext().getAudio().isSoundEnabled())
            {
                component.getBounds().x = x;
                component.getBounds().y = y;
                graphics.drawComponent(component);
                continue;
            }
            final float particleDelay = coords[i][2];
            coords[i][2] -= delta;
            if (particleDelay > 0)
            {
                component.getBounds().x = x;
                component.getBounds().y = y;
                graphics.drawComponent(component);
            }
            else
            {
                reset(component, i);
            }
        }
    }
}
