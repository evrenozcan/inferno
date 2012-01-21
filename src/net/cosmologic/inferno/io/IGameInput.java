/*
 * 
 */
package net.cosmologic.inferno.io;

/**
 * The Interface IGameInput.
 * 
 * @author Evren Ozcan
 */
public interface IGameInput
{

    /**
     * Checks if is key pressed.
     *
     * @param keyCode the key code
     * @return true, if is key pressed
     */
    boolean isKeyPressed(int keyCode);

    /**
     * Checks if is touch down.
     *
     * @param pointer the pointer
     * @return true, if is touch down
     */
    boolean isTouchDown(int pointer);

    /**
     * Gets the touch x.
     *
     * @param pointer the pointer
     * @return the touch x
     */
    int getTouchX(int pointer);

    /**
     * Gets the touch y.
     *
     * @param pointer the pointer
     * @return the touch y
     */
    int getTouchY(int pointer);

    /**
     * Gets the accel x.
     *
     * @return the accel x
     */
    float getAccelX();

    /**
     * Gets the accel y.
     *
     * @return the accel y
     */
    float getAccelY();

    /**
     * Gets the accel z.
     *
     * @return the accel z
     */
    float getAccelZ();

    /**
     * Sets the blocked.
     *
     * @param blocked the new blocked
     */
    void setBlocked(boolean blocked);

    /**
     * Vibrate.
     *
     * @param timeout the timeout
     */
    void vibrate(long timeout);
}
