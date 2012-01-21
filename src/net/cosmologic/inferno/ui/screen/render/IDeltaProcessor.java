/*
 * 
 */
package net.cosmologic.inferno.ui.screen.render;

import net.cosmologic.inferno.ui.graphics.IGraphics;
import net.cosmologic.inferno.ui.screen.IComponent;

/**
 * The Interface IDeltaProcessor.
 * 
 * @author Evren Ozcan
 */
public interface IDeltaProcessor
{
    
    /**
     * Update.
     *
     * @param component the component
     * @param delta the delta
     */
    void update(IComponent component, float delta);

    /**
     * Render.
     *
     * @param graphics the graphics
     * @param component the component
     * @param delta the delta
     */
    void render(IGraphics graphics, IComponent component, float delta);

    /**
     * Override listener.
     *
     * @param component the component
     * @param listener the listener
     */
    void overrideListener(IComponent component, IDeltaProcessListener listener);
}
