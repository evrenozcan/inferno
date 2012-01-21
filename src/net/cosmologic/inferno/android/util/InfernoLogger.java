/*
 * 
 */
package net.cosmologic.inferno.android.util;

import android.util.Log;

/**
 * The Class InfernoLogger.
 * 
 * @author Evren Ozcan
 */
public final class InfernoLogger
{

    /** The debug enabled. */
    private static boolean debugEnabled = false;

    /**
     * Debug.
     *
     * @param tag the tag
     * @param message the message
     */
    public static void debug(final String tag, final String message)
    {
        if (isDebugEnabled())
        {
            Log.d(tag, message);
        }
    }

    /**
     * Info.
     *
     * @param tag the tag
     * @param message the message
     */
    public static void info(final String tag, final String message)
    {
        Log.i(tag, message);
    }

    /**
     * Error.
     *
     * @param tag the tag
     * @param message the message
     */
    public static void error(final String tag, final String message)
    {
        Log.e(tag, message);
    }

    /**
     * Error.
     *
     * @param tag the tag
     * @param error the error
     */
    public static void error(final String tag, final Throwable error)
    {
        if (isDebugEnabled())
        {
            error.printStackTrace();
        }
        Log.e(tag, error.getMessage(), error);
    }

    /**
     * Checks if is debug enabled.
     *
     * @return true, if is debug enabled
     */
    public static boolean isDebugEnabled()
    {
        return debugEnabled;
    }

    /**
     * Instantiates a new inferno logger.
     */
    private InfernoLogger()
    {
    }
}
