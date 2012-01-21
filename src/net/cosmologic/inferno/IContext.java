/*
 * 
 */
package net.cosmologic.inferno;

import java.util.Set;

import net.cosmologic.inferno.io.IFileIO;
import net.cosmologic.inferno.io.IGameInput;
import net.cosmologic.inferno.reactor.ITribune;
import net.cosmologic.inferno.sound.IAudio;
import net.cosmologic.inferno.ui.screen.IGamePlatform;

/**
 * The Interface IContext.
 * 
 * @author Evren Ozcan
 */
public interface IContext
{

    /** The Constant ATTR_CONFIG_PREFIX. */
    public static final String ATTR_CONFIG_PREFIX = "config.";

    /**
     * Gets the attribute.
     * 
     * @param key
     *            the key
     * @return the attribute
     */
    Object getAttribute(String key);

    /**
     * Sets the attribute.
     * 
     * @param key
     *            the key
     * @param value
     *            the value
     */
    void setAttribute(String key, Object value);

    /**
     * Gets the game input.
     * 
     * @return the game input
     */
    IGameInput getGameInput();

    /**
     * Gets the file io.
     * 
     * @return the file io
     */
    IFileIO getFileIO();

    /**
     * Gets the audio.
     * 
     * @return the audio
     */
    IAudio getAudio();

    /**
     * Gets the tribune.
     * 
     * @return the tribune
     */
    ITribune getTribune();

    /**
     * Gets the game platform.
     * 
     * @return the game platform
     */
    IGamePlatform getGamePlatform();

    /**
     * Dispose.
     */
    void dispose();

    /**
     * Gets the attribute as boolean.
     * 
     * @param key
     *            the key
     * @return the attribute as boolean
     */
    boolean getAttributeAsBoolean(String key);

    /**
     * Gets the attribute as int.
     * 
     * @param key
     *            the key
     * @param defaultValue
     *            the default value
     * @return the attribute as int
     */
    int getAttributeAsInt(String key, int defaultValue);

    /**
     * Gets the keys.
     * 
     * @return the keys
     */
    Set<String> getKeys();

    /**
     * Reset configuration.
     */
    void resetConfiguration();

    /**
     * Clear configuration.
     * 
     * @param fileName
     *            the file name
     * @return true, if successful
     */
    boolean clearConfiguration(String fileName);

    /**
     * Checks if is updated.
     * 
     * @return true, if is updated
     */
    boolean isUpdated();

    /**
     * Gets the string resource.
     * 
     * @param id
     *            the id
     * @return the string resource
     */
    String getStringResource(int id);

    /**
     * Gets the color resource.
     * 
     * @param id
     *            the id
     * @return the color resource
     */
    int getColorResource(int id);

    /**
     * Gets the repository.
     * 
     * @return the repository
     */
    ResourceRepository getRepository();
}
