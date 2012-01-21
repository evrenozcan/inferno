/*
 * 
 */
package net.cosmologic.inferno.ui.screen;

import net.cosmologic.inferno.IContext;
import net.cosmologic.inferno.event.IGameEvent;
import net.cosmologic.inferno.ui.graphics.IGraphics;

/**
 * The Interface IContainer.
 * 
 * @author Evren Ozcan
 */
public interface IContainer extends IBitmapDrawable
{
    
    /**
     * Process input.
     *
     * @param event the event
     */
    void processInput(IGameEvent event);

    /**
     * Gets the graphics.
     *
     * @return the graphics
     */
    IGraphics getGraphics();

    /**
     * Gets the context.
     *
     * @return the context
     */
    IContext getContext();

    /**
     * On pause.
     */
    void onPause();

    /**
     * On resume.
     */
    void onResume();

    /**
     * Gets the width.
     *
     * @return the width
     */
    int getWidth();

    /**
     * Gets the height.
     *
     * @return the height
     */
    int getHeight();

    /**
     * Gets the component.
     *
     * @param componentId the component id
     * @return the component
     */
    IComponent getComponent(String componentId);

    /**
     * On interrupt.
     *
     * @return true, if successful
     */
    boolean onInterrupt();
}