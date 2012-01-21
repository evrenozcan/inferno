/*
 * 
 */
package net.cosmologic.inferno.ui.screen;

/**
 * The Interface IActionComponent.
 * 
 * @author Evren Ozcan
 */
public interface IActionComponent
{
    
    /**
     * Checks if is enabled.
     *
     * @return true, if is enabled
     */
    boolean isEnabled();

    /**
     * Sets the enabled.
     *
     * @param enabled the new enabled
     */
    void setEnabled(boolean enabled);

    /**
     * Sets the context update type.
     *
     * @param type the new context update type
     */
    void setContextUpdateType(ContextUpdateType type);

    /**
     * Gets the context update type.
     *
     * @return the context update type
     */
    ContextUpdateType getContextUpdateType();

    /**
     * Sets the context key.
     *
     * @param key the new context key
     */
    void setContextKey(String key);

    /**
     * Gets the context key.
     *
     * @return the context key
     */
    String getContextKey();

    /**
     * The Enum ContextUpdateType.
     */
    public enum ContextUpdateType
    {
        
        /** The BOOLEAN. */
        BOOLEAN, 
 /** The STRING. */
 STRING, 
 /** The NUMBER. */
 NUMBER;
    }
}
