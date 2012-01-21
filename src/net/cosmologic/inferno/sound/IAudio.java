/*
 * 
 */
package net.cosmologic.inferno.sound;

/**
 * The Interface IAudio.
 * 
 * @author Evren Ozcan
 */
public interface IAudio
{
    
    /**
     * Creates the track.
     *
     * @param fileName the file name
     * @return the i track
     */
    ITrack createTrack(String fileName);

    /**
     * Creates the sound effect.
     *
     * @param fileName the file name
     * @return the i sound effect
     */
    ISoundEffect createSoundEffect(String fileName);

    /**
     * Checks if is sound enabled.
     *
     * @return true, if is sound enabled
     */
    boolean isSoundEnabled();
}
