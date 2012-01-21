/*
 * 
 */
package net.cosmologic.inferno.android.handler;

import net.cosmologic.inferno.android.InfernoAndroidContext;
import net.cosmologic.inferno.event.GenericEvent;
import net.cosmologic.inferno.event.IGameEvent.EventType;
import net.cosmologic.inferno.io.TouchEventWrapper;
import android.view.MotionEvent;
import android.view.View;

/**
 * The Class TouchHandler.
 * 
 * @author Evren Ozcan
 */
public class TouchHandler implements ITouchHandler
{

    /** The context. */
    private final InfernoAndroidContext context;
    
    /** The touched. */
    private boolean touched;
    
    /** The y. */
    private int x, y;
    
    /** The scale y. */
    private final float scaleX, scaleY;
    
    /** The blocked. */
    private volatile boolean blocked;

    /**
     * Instantiates a new touch handler.
     *
     * @param context the context
     * @param scaleX the scale x
     * @param scaleY the scale y
     */
    public TouchHandler(final InfernoAndroidContext context, final float scaleX, final float scaleY)
    {
        this.context = context;
        context.getActivity().getView().setOnTouchListener(this);
        this.scaleX = scaleX;
        this.scaleY = scaleY;
    }

    /**{@inheritDoc}*/
    @Override
    public boolean onTouch(final View v, final MotionEvent event)
    {
        if (blocked)
        {
            return true;
        }
        sleep();
        final TouchEventWrapper wrapper = new TouchEventWrapper();
        final GenericEvent genericEvent = new GenericEvent(this, wrapper, EventType.TOUCH);
        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                genericEvent.action = EventType.Action.TOUCH_DOWN;
                touched = true;
                break;
            case MotionEvent.ACTION_MOVE:
                genericEvent.action = EventType.Action.TOUCH_DRAGGED;
                touched = true;
                break;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                genericEvent.action = EventType.Action.TOUCH_UP;
                touched = false;
                break;
        }
        wrapper.x = x = (int) (event.getX() * scaleX);
        wrapper.y = y = (int) (event.getY() * scaleY);
        context.getTribune().queueInput(genericEvent);
        return true;
    }

    /**
     * Sleep.
     */
    private void sleep()
    {
        try
        {
            Thread.sleep(16);
        }
        catch (final InterruptedException e)
        {
        }
    }

    /**{@inheritDoc}*/
    @Override
    public boolean isTouchDown(final int pointer)
    {
        synchronized (this)
        {
            if (pointer == 0)
            {
                return touched;
            }
            else
            {
                return false;
            }
        }
    }

    /**{@inheritDoc}*/
    @Override
    public int getX(final int pointer)
    {
        return x;
    }

    /**{@inheritDoc}*/
    @Override
    public int getY(final int pointer)
    {
        return y;
    }

    /**{@inheritDoc}*/
    @Override
    public boolean isBlocked()
    {
        return blocked;
    }

    /**{@inheritDoc}*/
    @Override
    public void setBlocked(final boolean blocked)
    {
        this.blocked = blocked;
    }
}
