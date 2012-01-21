/*
 * 
 */
package net.cosmologic.inferno.error;

/**
 * The Class InfernoException.
 * 
 * @author Evren Ozcan
 */
public class InfernoException extends RuntimeException
{

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -3123555564865920629L;

    /**
     * Instantiates a new inferno exception.
     *
     * @param message the message
     */
    public InfernoException(final String message)
    {
        super(message);
    }

    /**
     * Instantiates a new inferno exception.
     *
     * @param message the message
     * @param cause the cause
     */
    public InfernoException(final String message, final Throwable cause)
    {
        super(message, cause);
    }

    /**
     * Instantiates a new inferno exception.
     *
     * @param cause the cause
     */
    public InfernoException(final Throwable cause)
    {
        super(cause);
    }
}
