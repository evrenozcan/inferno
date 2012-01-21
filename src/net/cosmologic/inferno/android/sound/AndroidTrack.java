/*
 * 
 */
package net.cosmologic.inferno.android.sound;

import net.cosmologic.inferno.sound.ITrack;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;

/**
 * The Class AndroidTrack.
 * 
 * @author Evren Ozcan
 */
public class AndroidTrack implements ITrack, OnCompletionListener
{

    /** The player. */
    private final MediaPlayer player;
    
    /** The ready. */
    private boolean ready = false;
    
    /** The audio. */
    private final AndroidAudio audio;
    
    /** The id. */
    private final String id;

    /** The shared. */
    private boolean shared = true;

    /**
     * Instantiates a new android track.
     *
     * @param id the id
     * @param audio the audio
     * @param assetDescriptor the asset descriptor
     * @throws Exception the exception
     */
    public AndroidTrack(final String id, final AndroidAudio audio, final AssetFileDescriptor assetDescriptor)
            throws Exception
    {
        this.id = id;
        this.audio = audio;
        player = new MediaPlayer();
        player.setDataSource(assetDescriptor.getFileDescriptor(), assetDescriptor.getStartOffset(),
                assetDescriptor.getLength());
        player.prepare();
        setVolume(0.5f);
        ready = true;
        player.setOnCompletionListener(this);
    }

    /**{@inheritDoc}*/
    @Override
    public String getId()
    {
        return id;
    }

    /**{@inheritDoc}*/
    @Override
    public void play()
    {
        if (audio.isSoundEnabled() && !isPlaying())
        {
            try
            {
                synchronized (this)
                {
                    if (!ready)
                    {
                        player.prepare();
                    }
                    setVolume(0.5f);
                    player.start();
                }
            }
            catch (final Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    /**{@inheritDoc}*/
    @Override
    public void fadeOut()
    {
        if (!isPlaying())
        {
            return;
        }
        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                float volume = 0.5f;
                while (volume > 0)
                {
                    volume -= 0.02f;
                    setVolume(volume);
                    try
                    {
                        Thread.sleep(30);
                    }
                    catch (final InterruptedException e)
                    {
                    }
                }
                stop();
                setVolume(0.5f);
            }

        }, "Android").start();
    }

    /**{@inheritDoc}*/
    @Override
    public void fadeOutAndDispose()
    {
        if (!isPlaying())
        {
            return;
        }
        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                if (isPlaying())
                {
                    float volume = 0.5f;
                    while (volume > 0)
                    {
                        volume -= 0.02f;
                        setVolume(volume);
                        try
                        {
                            Thread.sleep(30);
                        }
                        catch (final InterruptedException e)
                        {
                        }
                    }
                }
                dispose();
            }

        }, "Android").start();
    }

    /**{@inheritDoc}*/
    @Override
    public void stop()
    {
        player.stop();
        synchronized (this)
        {
            ready = false;
        }
    }

    /**{@inheritDoc}*/
    @Override
    public void pause()
    {
        if (isPlaying())
        {
            player.pause();
        }
    }

    /**{@inheritDoc}*/
    @Override
    public void loop()
    {
        player.setLooping(true);
        play();
    }

    /**{@inheritDoc}*/
    @Override
    public void playOnce()
    {
        play();
        player.setLooping(false);
    }

    /**{@inheritDoc}*/
    @Override
    public void setVolume(final float value)
    {
        player.setVolume(value, value);
    }

    /**{@inheritDoc}*/
    @Override
    public boolean isPlaying()
    {
        return player.isPlaying();
    }

    /**{@inheritDoc}*/
    @Override
    public boolean isStopped()
    {
        return !ready;
    }

    /**{@inheritDoc}*/
    @Override
    public boolean isLooping()
    {
        return player.isLooping();
    }

    /**{@inheritDoc}*/
    @Override
    public void dispose()
    {
        if (player.isPlaying())
        {
            player.stop();
        }
        player.release();
        ready = false;
        audio.getContext().getRepository().removeResource(this);
    }
    /**{@inheritDoc}*/
    @Override
    public void onCompletion(final MediaPlayer mp)
    {
        synchronized (this)
        {
            ready = false;
        }
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
