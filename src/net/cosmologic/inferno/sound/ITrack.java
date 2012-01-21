/*
 * 
 */
package net.cosmologic.inferno.sound;

import net.cosmologic.inferno.IResource;

/**
 * The Interface ITrack.
 * 
 * @author Evren Ozcan
 */
public interface ITrack extends IResource
{
    
    /**
     * Play.
     */
    void play();

    /**
     * Stop.
     */
    void stop();

    /**
     * Pause.
     */
    void pause();

    /**
     * Loop.
     */
    void loop();

    /**
     * Play once.
     */
    void playOnce();

    /**
     * Sets the volume.
     *
     * @param value the new volume
     */
    void setVolume(float value);

    /**
     * Checks if is playing.
     *
     * @return true, if is playing
     */
    boolean isPlaying();

    /**
     * Checks if is stopped.
     *
     * @return true, if is stopped
     */
    boolean isStopped();

    /**
     * Checks if is looping.
     *
     * @return true, if is looping
     */
    boolean isLooping();

    /**
     * Dispose.
     */
    void dispose();

    /**
     * Fade out.
     */
    void fadeOut();

    /**
     * Fade out and dispose.
     */
    void fadeOutAndDispose();
}
