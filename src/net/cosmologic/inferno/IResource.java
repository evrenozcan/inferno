/*
 * 
 */
package net.cosmologic.inferno;

/**
 * The Interface IResource.
 * 
 * @author Evren Ozcan
 */
public interface IResource
{

    /**
     * Gets the id.
     * 
     * @return the id
     */
    String getId();

    /**
     * Checks if is shared.
     * 
     * @return true, if is shared
     */
    boolean isShared();

    /**
     * Sets the shared.
     * 
     * @param shared
     *            the new shared
     */
    void setShared(boolean shared);
}
