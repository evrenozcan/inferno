/*
 * 
 */
package net.cosmologic.inferno.io;

/**
 * The Class TouchEventWrapper.
 * 
 * @author Evren Ozcan
 */
public class TouchEventWrapper
{
    
    /** The y. */
    public int x, y;
    
    /** The pointer. */
    public int pointer;

    /**
     * Instantiates a new touch event wrapper.
     */
    public TouchEventWrapper()
    {

    }

    /**
     * Instantiates a new touch event wrapper.
     *
     * @param x the x
     * @param y the y
     * @param pointer the pointer
     */
    public TouchEventWrapper(final int x, final int y, final int pointer)
    {
        this.x = x;
        this.y = y;
        this.pointer = pointer;
    }
}
