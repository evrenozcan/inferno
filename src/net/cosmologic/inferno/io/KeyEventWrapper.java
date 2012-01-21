/*
 * 
 */
package net.cosmologic.inferno.io;

/**
 * The Class KeyEventWrapper.
 * 
 * @author Evren Ozcan
 */
public class KeyEventWrapper
{
    
    /** The key code. */
    public int keyCode;
    
    /** The key char. */
    public char keyChar;

    /**
     * Instantiates a new key event wrapper.
     */
    public KeyEventWrapper()
    {

    }

    /**
     * Instantiates a new key event wrapper.
     *
     * @param keyCode the key code
     * @param keyChar the key char
     */
    public KeyEventWrapper(final int keyCode, final char keyChar)
    {
        this.keyCode = keyCode;
        this.keyChar = keyChar;
    }
}
