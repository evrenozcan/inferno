/*
 * 
 */
package net.cosmologic.inferno.sound;

import net.cosmologic.inferno.IResource;

/**
 * The Interface ISoundEffect.
 * 
 * @author Evren Ozcan
 */
public interface ISoundEffect extends IResource
{
    
    /**
     * Stop.
     */
    void stop();

    /**
     * Play.
     *
     * @param volume the volume
     */
    void play(float volume);

    /**
     * Loop.
     *
     * @param volume the volume
     */
    void loop(float volume);

    /**
     * Dispose.
     */
    void dispose();
}
