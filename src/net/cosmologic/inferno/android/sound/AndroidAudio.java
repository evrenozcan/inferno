/*
 * 
 */
package net.cosmologic.inferno.android.sound;

import net.cosmologic.inferno.InfernoException;
import net.cosmologic.inferno.android.Constants;
import net.cosmologic.inferno.android.InfernoAndroidContext;
import net.cosmologic.inferno.sound.IAudio;
import net.cosmologic.inferno.sound.ISoundEffect;
import net.cosmologic.inferno.sound.ITrack;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;

/**
 * The Class AndroidAudio.
 * 
 * @author Evren Ozcan
 */
public class AndroidAudio implements IAudio
{

    /** The assests. */
    private final AssetManager assests;
    
    /** The sound pool. */
    private final SoundPool soundPool;
    
    /** The context. */
    private final InfernoAndroidContext context;
    
    /** The Constant ASSET_PATH. */
    private static final String ASSET_PATH = "audio/%s";

    /**
     * Instantiates a new android audio.
     *
     * @param context the context
     */
    public AndroidAudio(final InfernoAndroidContext context)
    {
        this.context = context;
        context.getActivity().setVolumeControlStream(AudioManager.STREAM_MUSIC);
        this.assests = context.getActivity().getAssets();
        this.soundPool = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
    }

    /**{@inheritDoc}*/
    @Override
    public ITrack createTrack(final String fileName)
    {
        try
        {
            return new AndroidTrack(fileName, this, assests.openFd(String.format(ASSET_PATH, fileName)));
        }
        catch (final Exception e)
        {
            throw new InfernoException(String.format("Can not load track: %s", fileName), e);
        }
    }

    /**{@inheritDoc}*/
    @Override
    public ISoundEffect createSoundEffect(final String fileName)
    {
        try
        {
            return new AndroidSoundEffect(fileName, this, soundPool, soundPool.load(
                    assests.openFd(String.format(ASSET_PATH, fileName)), 0));
        }
        catch (final Exception e)
        {
            throw new InfernoException(String.format("Can not load sound: %s", fileName), e);
        }
    }

    /**
     * Gets the context.
     *
     * @return the context
     */
    public InfernoAndroidContext getContext()
    {
        return context;
    }

    /**{@inheritDoc}*/
    @Override
    public boolean isSoundEnabled()
    {
        return context.getAttributeAsBoolean(Constants.ATTRIBUTE_SOUND_ENABLED);
    }

    /**
     * Enable sound.
     *
     * @param value the value
     */
    public void enableSound(final boolean value)
    {
        context.setAttribute(Constants.ATTRIBUTE_SOUND_ENABLED, value);
    }
}
