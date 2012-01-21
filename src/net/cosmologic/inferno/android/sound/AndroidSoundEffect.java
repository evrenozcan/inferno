/*
 * 
 */
package net.cosmologic.inferno.android.sound;

import net.cosmologic.inferno.sound.ISoundEffect;
import android.media.SoundPool;

/**
 * The Class AndroidSoundEffect.
 * 
 * @author Evren Ozcan
 */
public class AndroidSoundEffect implements ISoundEffect
{
    
    /** The internal id. */
    private final int internalId;
    
    /** The pool. */
    private final SoundPool pool;
    
    /** The audio. */
    private final AndroidAudio audio;
    
    /** The id. */
    private final String id;

    /** The shared. */
    private boolean shared = true;

    /**
     * Instantiates a new android sound effect.
     *
     * @param id the id
     * @param audio the audio
     * @param pool the pool
     * @param internalId the internal id
     */
    public AndroidSoundEffect(final String id, final AndroidAudio audio, final SoundPool pool, final int internalId)
    {
        this.audio = audio;
        this.pool = pool;
        this.id = id;
        this.internalId = internalId;
    }

    /**{@inheritDoc}*/
    @Override
    public void play(final float volume)
    {
        if (audio.isSoundEnabled())
        {
            pool.stop(internalId);
            pool.play(internalId, volume, volume, 0, 0, 0);
        }
    }

    /**{@inheritDoc}*/
    @Override
    public String getId()
    {
        return id;
    }

    /**{@inheritDoc}*/
    @Override
    public void loop(final float volume)
    {
        if (audio.isSoundEnabled())
        {
            pool.play(internalId, volume, volume, 0, 1, 1);
        }
    }

    /**{@inheritDoc}*/
    @Override
    public void stop()
    {
        pool.stop(internalId);
    }

    /**{@inheritDoc}*/
    @Override
    public void dispose()
    {
        stop();
        pool.unload(internalId);
        audio.getContext().getRepository().removeResource(this);
    }

    /**{@inheritDoc}*/
    @Override
    public boolean isShared()
    {
        return shared;
    }

    /**{@inheritDoc}*/
    @Override
    public void setShared(final boolean shared)
    {
        this.shared = shared;
    }
}
