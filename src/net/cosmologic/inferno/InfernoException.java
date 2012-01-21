/*
 * 
 */
package net.cosmologic.inferno;

/**
 * The Class InfernoException.
 * 
 * @author Evren Ozcan
 */
public class InfernoException extends RuntimeException
{

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 8691468352652229326L;

    /**
     * Instantiates a new inferno exception.
     */
    public InfernoException()
    {
        super();
    }

    /**
     * Instantiates a new inferno exception.
     * 
     * @param message
     *            the message
     * @param cause
     *            the cause
     */
    public InfernoException(final String message, final Throwable cause)
    {
        super(message, cause);
    }

    /**
     * Instantiates a new inferno exception.
     * 
     * @param message
     *            the message
     */
    public InfernoException(final String message)
    {
        super(message);
    }

    /**
     * Instantiates a new inferno exception.
     * 
     * @param cause
     *            the cause
     */
    public InfernoException(final Throwable cause)
    {
        super(cause);
    }
}
