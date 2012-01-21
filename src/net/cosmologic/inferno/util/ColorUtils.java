/*
 * 
 */
package net.cosmologic.inferno.util;

/**
 * The Class ColorUtils.
 * 
 * @author Evren Ozcan
 */
public final class ColorUtils
{

    /**
     * Instantiates a new color utils.
     */
    private ColorUtils()
    {
        assert false : "Fully static class";
    }

    /**
     * Convert.
     *
     * @param r the r
     * @param g the g
     * @param b the b
     * @param a the a
     * @return the int
     */
    public static int convert(final int r, final int g, final int b, final int a)
    {
        return ((a & 0xff) << 24) | ((r & 0xff) << 16) | ((g & 0xff) << 8) | ((b & 0xff));
    }
}
