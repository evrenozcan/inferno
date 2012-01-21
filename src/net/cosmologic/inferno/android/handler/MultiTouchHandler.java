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
 * The Class MultiTouchHandler.
 * @author Evren Ozcan
 */
public class MultiTouchHandler implements ITouchHandler
{

    /** The touched. */
    private final boolean[] touched = new boolean[20];
    
    /** The context. */
    private final InfernoAndroidContext context;
    
    /** The x. */
    private final int[] x = new int[20];
    
    /** The y. */
    private final int[] y = new int[20];
    
    /** The scale y. */
    private final float scaleX, scaleY;
    
    /** The blocked. */
    private volatile boolean blocked;

    /**
     * Instantiates a new multi touch handler.
     *
     * @param context the context
     * @param scaleX the scale x
     * @param scaleY the scale y
     */
    public MultiTouchHandler(final InfernoAndroidContext context, final float scaleX, final float scaleY)
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
        final int action = event.getAction() & MotionEvent.ACTION_MASK;
        int pointerIndex = (event.getAction() & MotionEvent.ACTION_POINTER_ID_MASK) >> MotionEvent.ACTION_POINTER_ID_SHIFT;
        int pointerId = event.getPointerId(pointerIndex);
        final TouchEventWrapper wrapper = new TouchEventWrapper();
        final GenericEvent genericEvent = new GenericEvent(this, wrapper, EventType.TOUCH);
        switch (action)
        {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN:
                genericEvent.action = EventType.Action.TOUCH_DOWN;
                wrapper.pointer = pointerId;
                wrapper.x = x[pointerId] = (int) (event.getX(pointerIndex) * scaleX);
                wrapper.y = y[pointerId] = (int) (event.getY(pointerIndex) * scaleY);
                touched[pointerId] = true;
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
            case MotionEvent.ACTION_CANCEL:
                genericEvent.action = EventType.Action.TOUCH_UP;
                wrapper.pointer = pointerId;
                wrapper.x = x[pointerId] = (int) (event.getX(pointerIndex) * scaleX);
                wrapper.y = y[pointerId] = (int) (event.getY(pointerIndex) * scaleY);
                touched[pointerId] = false;
                break;

            case MotionEvent.ACTION_MOVE:
                final int pointerCount = event.getPointerCount();
                for (int i = 0; i < pointerCount; i++)
                {
                    pointerIndex = i;
                    pointerId = event.getPointerId(pointerIndex);
                    genericEvent.action = EventType.Action.TOUCH_DRAGGED;
                    wrapper.pointer = pointerId;
                    wrapper.x = x[pointerId] = (int) (event.getX(pointerIndex) * scaleX);
                    wrapper.y = y[pointerId] = (int) (event.getY(pointerIndex) * scaleY);
                }
                break;
        }

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
            if ((pointer < 0) || (pointer >= 20))
            {
                return false;
            }
            else
            {
                return touched[pointer];
            }
        }
    }

    /**{@inheritDoc}*/
    @Override
    public int getX(final int pointer)
    {
        return getCoord(x, pointer);
    }

    /**
     * Gets the coord.
     *
     * @param coordArray the coord array
     * @param pointer the pointer
     * @return the coord
     */
    private int getCoord(final int[] coordArray, final int pointer)
    {
        synchronized (this)
        {
            return coordArray[pointer];
        }
    }

    /**{@inheritDoc}*/
    @Override
    public int getY(final int pointer)
    {
        return getCoord(y, pointer);
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
