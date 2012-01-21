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
 * The Class ReactorProcess.
 * 
 * @author Evren Ozcan
 */
public abstract class ReactorProcess implements IReactorProcess
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

    /**{@inheritDoc}*/
    @Override
    public boolean isAsynch()
    {
        return false;
    }

    /**
     * Instantiates a new reactor process.
     *
     * @param precision the precision
     */
    protected ReactorProcess(final float precision)
    {
        this.precision = precision;
    }

    /**{@inheritDoc}*/
    @Override
    public void setTribune(final ITribune tribune)
    {
        this.tribune = tribune;
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
            updateInternal(delta);
        }
        catch (final Exception e)
        {
            if (null != tribune)
            {
                // tribune.removeProcess(this);
            }
        }

    }

    /**
     * Update internal.
     *
     * @param delta the delta
     */
    protected abstract void updateInternal(float delta);

    /**
     * Notify listeners on end.
     */
    protected void notifyListenersOnEnd()
    {
        if ((null != tribune) && !repeat)
        {
            // tribune.removeProcess(this);
        }
        if (null != listeners)
        {
            for (final IReactorProcessListener listener : listeners)
            {
                try
                {
                    listener.onProcessEnd(this);
                }
                catch (final Exception e)
                {
                    // TODO
                }
            }
        }
        if (repeat)
        {
            reset();
        }
    }

    /**{@inheritDoc}*/
    @Override
    public void reset()
    {
        elapsed = 0f;
    }

    /**{@inheritDoc}*/
    @Override
    public void setRepeat(final boolean repeat)
    {
        this.repeat = repeat;
    }
}
