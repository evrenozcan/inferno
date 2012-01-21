/*
 * 
 */
package net.cosmologic.inferno.reactor;

import java.util.concurrent.TimeUnit;

import net.cosmologic.inferno.IContext;
import net.cosmologic.inferno.InfernoException;
import net.cosmologic.inferno.android.Constants;
import net.cosmologic.inferno.event.IGameEvent;
import net.cosmologic.inferno.event.IGameEvent.EventType;
import net.cosmologic.inferno.sound.ISoundEffect;
import net.cosmologic.inferno.util.Pool;

/**
 * The Class AbstractTribune.
 * 
 * @author Evren Ozcan
 */
public abstract class AbstractTribune implements ITribune
{
    
    /** The context. */
    protected final IContext context;
    
    /** The tribune. */
    private Thread tribune = null;
    
    /** The active. */
    private boolean active = false;
    
    /** The processes. */
    private final Pool<IReactorProcess> processes;
    
    /** The effects. */
    private final Pool<ISoundEffect> effects;
    
    /** The inputs. */
    private final Pool<IGameEvent> inputs;

    /**
     * Pre start.
     */
    protected abstract void preStart();

    /**
     * On next frame.
     *
     * @param delta the delta
     */
    protected abstract void onNextFrame(float delta);

    /** The Constant FPS. */
    public static final int FPS = 24;

    /**
     * On loop end.
     */
    protected void onLoopEnd()
    {
    }

    /**
     * Instantiates a new abstract tribune.
     *
     * @param context the context
     */
    protected AbstractTribune(final IContext context)
    {
        this.context = context;
        processes = new Pool<IReactorProcess>(context.getAttributeAsInt(Constants.ATTRIBUTE_ROCESS_QUEUE_SIZE, 10));
        effects = new Pool<ISoundEffect>(context.getAttributeAsInt(Constants.ATTRIBUTE_SOUND_QUEUE_SIZE, 10));
        inputs = new Pool<IGameEvent>(context.getAttributeAsInt(Constants.ATTRIBUTE_INPUT_QUEUE_SIZE, 10));
    }

    /**{@inheritDoc}*/
    @Override
    public void start()
    {
        if (active)
        {
            throw new InfernoException("Tribune is already running");
        }
        tribune = new Thread(this, "Inferno-Tribune");
        tribune.start();
    }

    /**{@inheritDoc}*/
    @Override
    public boolean isActive()
    {
        return active;
    }

    /**{@inheritDoc}*/
    @Override
    public void restart()
    {
        turnOff();
        start();
    }

    /**{@inheritDoc}*/
    @Override
    public void turnOff()
    {
        if (!active)
        {
            return;
        }
        active = false;
        while (true)
        {
            try
            {
                tribune.join();
                break;
            }
            catch (final Exception e)
            {

            }
        }
    }

    /**{@inheritDoc}*/
    @Override
    public void run()
    {
        active = true;
        preStart();
        long startTime = System.nanoTime();
        while (active)
        {
            final float delta = (System.nanoTime() - startTime) / 1000000000.0f;
            startTime = System.nanoTime();
            processTasks(delta);
            processInputs();
            processEffects();
            try
            {
                onNextFrame(delta);
            }
            catch (final Exception e)
            {
                e.printStackTrace();
                throw new InfernoException(e);
            }
            synchFps((int) ((1.0f / delta)));
        }
        onLoopEnd();
    }

    /**
     * Synch fps.
     *
     * @param fpsSynch the fps synch
     */
    private void synchFps(final int fpsSynch)
    {
        if ((fpsSynch < 120) && (fpsSynch > FPS))
        {
            try
            {
                TimeUnit.MILLISECONDS.sleep(1000 / FPS - 1000 / fpsSynch);
            }
            catch (final Exception e)
            {
            }
        }
    }

    /**
     * Process tasks.
     *
     * @param delta the delta
     */
    private void processTasks(final float delta)
    {
        int limit = processes.getSize();
        while (processes.hasPending() && (limit-- > 0))
        {
            try
            {
                processes.poll().update(delta);
            }
            catch (final Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    /**
     * Process effects.
     */
    private void processEffects()
    {
        int limit = effects.getSize();
        while (effects.hasPending() && (limit-- > 0))
        {
            try
            {
                effects.poll().play(0.8f);
            }
            catch (final Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    /**
     * Process inputs.
     */
    private void processInputs()
    {
        int limit = inputs.getSize();
        while (inputs.hasPending() && (limit-- > 0))
        {
            try
            {
                context.getGamePlatform().processInput(inputs.poll());
            }
            catch (final Exception e)
            {
                e.printStackTrace();
                throw new InfernoException(e);
            }
        }
    }

    /**{@inheritDoc}*/
    @Override
    public void chillOut()
    {
    }

    /**{@inheritDoc}*/
    @Override
    public final void queueProcess(final IReactorProcess process)
    {
        if (null != process)
        {
            process.setTribune(this);
            processes.offerQuietely(process);
        }
    }

    /**{@inheritDoc}*/
    @Override
    public final void queueInput(final IGameEvent input)
    {
        if (null != input)
        {
            if (input.getType() == EventType.INTERNAL)
            {
                inputs.offerQuietely(input, true);
            }
            else
            {
                inputs.offerQuietely(input);
            }
        }
    }

    /**{@inheritDoc}*/
    @Override
    public void queueSoundEffect(final ISoundEffect effect)
    {
        if (null != effect)
        {
            effects.offerQuietely(effect);
        }
    }
}
