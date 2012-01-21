/*
 * 
 */
package net.cosmologic.inferno.util;

/**
 * The Class NumberUtils.
 * 
 * @author Evren Ozcan
 */
public final class NumberUtils
{
    
    /** The Constant UNASSIGNED_ID. */
    public static final int UNASSIGNED_ID = -1;

    /**
     * Instantiates a new number utils.
     */
    private NumberUtils()
    {
    }

    /**
     * Gets the int.
     *
     * @param value the value
     * @param defaultValue the default value
     * @return the int
     */
    public static int getInt(final String value, final int defaultValue)
    {
        try
        {
            return Integer.parseInt(value);
        }
        catch (final NumberFormatException ne)
        {
            return defaultValue;
        }
    }
}
