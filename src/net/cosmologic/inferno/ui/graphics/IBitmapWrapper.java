/*
 * 
 */
package net.cosmologic.inferno.ui.graphics;

import net.cosmologic.inferno.IResource;

/**
 * The Interface IBitmapWrapper.
 * 
 * @author Evren Ozcan
 */
public interface IBitmapWrapper extends IResource
{

    /**
     * The Enum BitmapFormat.
     */
    public enum BitmapFormat
    {

        /** The ARG b8888. */
        ARGB8888,
        /** The ARG b4444. */
        ARGB4444,
        /** The RG b565. */
        RGB565;
    }

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
     * Gets the format.
     * 
     * @return the format
     */
    BitmapFormat getFormat();

    /**
     * Dispose.
     */
    void dispose();

    /**
     * Gets the raw.
     * 
     * @return the raw
     */
    Object getRaw();
}
