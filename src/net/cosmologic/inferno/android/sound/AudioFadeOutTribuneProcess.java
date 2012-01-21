/*
 * 
 */
package net.cosmologic.inferno.android.sound;

import net.cosmologic.inferno.reactor.impl.ReactorProcess;
import net.cosmologic.inferno.sound.ITrack;

/**
 * The Class AudioFadeOutTribuneProcess.
 * 
 * @author Evren Ozcan
 */
public class AudioFadeOutTribuneProcess extends ReactorProcess
{
    
    /** The track. */
    private final ITrack track;
    
    /** The volume. */
    private float volume = 0.5f;
    
    /** The step. */
    private final float step;

    /**
     * Instantiates a new audio fade out tribune process.
     *
     * @param track the track
     */
    public AudioFadeOutTribuneProcess(final ITrack track)
    {
        this(track, 0, 0.1f);
    }

    /**
     * Instantiates a new audio fade out tribune process.
     *
     * @param track the track
     * @param precision the precision
     * @param step the step
     */
    public AudioFadeOutTribuneProcess(final ITrack track, final float precision, final float step)
    {
        super(precision);
        this.track = track;
        this.step = step;
    }

    /**{@inheritDoc}*/
    @Override
    public void reset()
    {
        super.reset();
        volume = 0.5f;
    }

    /**{@inheritDoc}*/
    @Override
    public void updateInternal(final float delta)
    {
        if (!track.isPlaying())
        {
            return;
        }
        if ((precision == 0) || (elapsed >= precision))
        {
            volume -= step;
            if (volume >= 0)
            {
                track.setVolume(volume);
            }
            if (volume <= 0f)
            {
                // finish
                track.stop();
                notifyListenersOnEnd();
            }
            elapsed = 0;
        }
        else if (precision != 0)
        {
            elapsed += precision;
        }
    }

    /**{@inheritDoc}*/
    @Override
    public Object getResult()
    {
        return track;
    }
}
