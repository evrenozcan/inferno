/*
 * 
 */
package net.cosmologic.inferno.util;

/**
 * The Class StringUtils.
 * 
 * @author Evren Ozcan
 */
public final class StringUtils
{
    
    /** The Constant EMPTY. */
    public static final String EMPTY = "";

    /**
     * Instantiates a new string utils.
     */
    private StringUtils()
    {
    }

    /**
     * Checks if is blank.
     *
     * @param string the string
     * @return true, if is blank
     */
    public static boolean isBlank(final String string)
    {
        return ((null == string) || EMPTY.equals(string));
    }

    /**
     * Checks if is not blank.
     *
     * @param string the string
     * @return true, if is not blank
     */
    public static boolean isNotBlank(final String string)
    {
        return !isBlank(string);
    }

    /**
     * Equals.
     *
     * @param lhs the lhs
     * @param rhs the rhs
     * @return true, if successful
     */
    public static boolean equals(final String lhs, final String rhs)
    {
        if ((null == lhs) && (null == rhs))
        {
            return true;
        }
        if ((null == lhs) || (null == rhs))
        {
            return false;
        }
        return lhs.equals(rhs);
    }

    /**
     * Trim.
     *
     * @param text the text
     * @return the string
     */
    public static String trim(final String text)
    {
        if (null == text)
        {
            return null;
        }
        else
        {
            return text.trim();
        }
    }

    /**
     * Gets the string as pair.
     *
     * @param text the text
     * @return the string as pair
     */
    public static String[] getStringAsPair(final String text)
    {
        final String[] pair = new String[2];
        if (null == text)
        {
            return pair;
        }
        else
        {
            final String[] vkp = text.split("=");
            pair[0] = trim(vkp[0]);
            if (vkp.length > 1)
            {
                pair[1] = trim(vkp[1]);
            }
        }

        return pair;
    }
}
