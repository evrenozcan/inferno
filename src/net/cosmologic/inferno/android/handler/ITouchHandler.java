/*
 * 
 */
package net.cosmologic.inferno.android.handler;

import android.view.View.OnTouchListener;

/**
 * The Interface ITouchHandler.
 * 
 * @author Evren Ozcan
 */
public interface ITouchHandler extends OnTouchListener, IHandler
{
    
    /**
     * Checks if is touch down.
     *
     * @param pointer the pointer
     * @return true, if is touch down
     */
    boolean isTouchDown(int pointer);

    /**
     * Gets the x.
     *
     * @param pointer the pointer
     * @return the x
     */
    int getX(int pointer);

    /**
     * Gets the y.
     *
     * @param pointer the pointer
     * @return the y
     */
    int getY(int pointer);
}
