/*
 * 
 */
package net.cosmologic.inferno.sound;

/**
 * The Interface IAudioPlayer.
 * 
 * @author Evren Ozcan
 */
public interface IAudioPlayer
{
    
    /**
     * Play track.
     *
     * @param track the track
     */
    void playTrack(ITrack track);

    /**
     * Play sound effect.
     *
     * @param soundEffect the sound effect
     */
    void playSoundEffect(ISoundEffect soundEffect);

    /**
     * The Enum PlayMode.
     */
    enum PlayMode
    {
        
        /** The TRAC k_ once. */
        TRACK_ONCE, 
 /** The REPEA t_ track. */
 REPEAT_TRACK, 
 /** The SHUFFLE. */
 SHUFFLE, 
 /** The REPEA t_ all. */
 REPEAT_ALL, 
 /** The SHUFFL e_ repeat. */
 SHUFFLE_REPEAT;
    }
}
