/*
 * 
 */
package net.cosmologic.inferno.ui.screen;

/**
 * The Interface IGamePlatform.
 * 
 * @author Evren Ozcan
 */
public interface IGamePlatform extends IContainer
{
    
    /**
     * Refresh frame.
     *
     * @param delta the delta
     */
    void refreshFrame(float delta);

    /**
     * Gets the display.
     *
     * @return the display
     */
    ILayer getDisplay();

    /**
     * End.
     */
    void end();

    /**
     * On finish.
     */
    void onFinish();
}
