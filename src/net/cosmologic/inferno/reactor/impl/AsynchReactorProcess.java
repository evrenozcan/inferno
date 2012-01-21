/*
 * 
 */
package net.cosmologic.inferno.reactor.impl;

import java.util.ArrayList;
import java.util.List;

import net.cosmologic.inferno.reactor.IReactorProcess;
import net.cosmologic.inferno.reactor.IReactorProcessListener;
import net.cosmologic.inferno.reactor.ITribune;

/**
 * The Class AsynchReactorProcess.
 * 
 * @author Evren Ozcan
 */
public abstract class AsynchReactorProcess implements IReactorProcess
{

    /** The listeners. */
    protected List<IReactorProcessListener> listeners;
    
    /** The repeat. */
    protected boolean repeat;
    
    /** The tribune. */
    private ITribune tribune;
    
    /** The precision. */
    protected float precision;
    
    /** The elapsed. */
    protected float elapsed;
    
    /** The name. */
    private final String name;
    
    /** The result. */
    private Object result;
    
    /** The delay. */
    private long delay = 0;

    /**
     * Instantiates a new asynch reactor process.
     *
     * @param name the name
     * @param precision the precision
     */
    protected AsynchReactorProcess(final String name, final float precision)
    {
        this.precision = precision;
        this.name = name;
    }

    /**{@inheritDoc}*/
    @Override
    public void reset()
    {
        result = null;
        delay = 0;
    }

    /**{@inheritDoc}*/
    @Override
    public void setTribune(final ITribune tribune)
    {
        this.tribune = tribune;
    }

    /**{@inheritDoc}*/
    @Override
    public boolean isAsynch()
    {
        return true;
    }

    /**{@inheritDoc}*/
    @Override
    public void addProcessListener(final IReactorProcessListener callback)
    {
        if (null == listeners)
        {
            listeners = new ArrayList<IReactorProcessListener>();
        }
        listeners.add(callback);
    }

    /**{@inheritDoc}*/
    @Override
    public final void update(final float delta)
    {
        try
        {
            final IReactorProcess instance = this;
            final Thread t = new Thread(new Runnable()
            {
                @Override
                public void run()
                {
                    try
                    {
                        Thread.sleep(delay);
                    }
                    catch (final InterruptedException e1)
                    {
                    }
                    result = updateInternal(delta);
                    reset();
                    if (null != listeners)
                    {
                        for (final IReactorProcessListener listener : listeners)
                        {
                            try
                            {
                                listener.onProcessEnd(instance);
                            }
                            catch (final Exception e)
                            {
                            }
                        }
                    }
                    if (repeat)
                    {
                        tribune.queueProcess(instance);
                    }
                }
            }, "ReactorProcess-" + name);
            t.join();
            t.start();
        }
        catch (final Exception e)
        {
        }
    }

    /**
     * Sets the delay.
     *
     * @param delay the new delay
     */
    public void setDelay(final long delay)
    {
        this.delay = delay;
    }

    /**{@inheritDoc}*/
    @Override
    public Object getResult()
    {
        return result;
    }

    /**
     * Update internal.
     *
     * @param delta the delta
     * @return the object
     */
    protected abstract Object updateInternal(float delta);

    /**{@inheritDoc}*/
    @Override
    public void setRepeat(final boolean repeat)
    {
        this.repeat = repeat;
    }
}
