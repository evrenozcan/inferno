/*
 * 
 */
package net.cosmologic.inferno.android.handler;

/**
 * The Interface IHandler.
 * 
 * @author Evren Ozcan
 */
public interface IHandler
{

    /**
     * Checks if is blocked.
     *
     * @return true, if is blocked
     */
    boolean isBlocked();

    /**
     * Sets the blocked.
     *
     * @param blocked the new blocked
     */
    void setBlocked(boolean blocked);
}
