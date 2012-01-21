/*
 * 
 */
package net.cosmologic.inferno.android.io;

import net.cosmologic.inferno.android.Constants;
import net.cosmologic.inferno.android.InfernoAndroidContext;
import net.cosmologic.inferno.android.handler.AccelerometerHandler;
import net.cosmologic.inferno.android.handler.ITouchHandler;
import net.cosmologic.inferno.android.handler.MultiTouchHandler;
import net.cosmologic.inferno.android.handler.OnKeyEventHandler;
import net.cosmologic.inferno.android.handler.TouchHandler;
import net.cosmologic.inferno.io.IGameInput;
import android.content.Context;
import android.os.Build.VERSION;
import android.os.Vibrator;

/**
 * The Class AndroidGameInput.
 * 
 * @author Evren Ozcan
 */
public class AndroidGameInput implements IGameInput
{
    
    /** The accel handler. */
    private final AccelerometerHandler accelHandler;
    
    /** The key handler. */
    private final OnKeyEventHandler keyHandler;
    
    /** The touch handler. */
    private final ITouchHandler touchHandler;
    
    /** The vibrator. */
    private final Vibrator vibrator;
    
    /** The context. */
    private final InfernoAndroidContext context;

    /**
     * Instantiates a new android game input.
     *
     * @param context the context
     * @param scaleX the scale x
     * @param scaleY the scale y
     */
    public AndroidGameInput(final InfernoAndroidContext context, final float scaleX, final float scaleY)
    {
        this.context = context;
        accelHandler = new AccelerometerHandler(context.getActivity());
        keyHandler = new OnKeyEventHandler(context);
        if (Integer.valueOf(VERSION.SDK) < 5)
        {
            touchHandler = new TouchHandler(context, scaleX, scaleY);
        }
        else
        {
            touchHandler = new MultiTouchHandler(context, scaleX, scaleY);
        }
        // Get instance of Vibrator from current Context
        vibrator = (Vibrator) context.getActivity().getSystemService(Context.VIBRATOR_SERVICE);
    }

    /**{@inheritDoc}*/
    @Override
    public boolean isKeyPressed(final int keyCode)
    {
        return keyHandler.isKeyPressed(keyCode);
    }

    /**{@inheritDoc}*/
    @Override
    public boolean isTouchDown(final int pointer)
    {
        return touchHandler.isTouchDown(pointer);
    }

    /**{@inheritDoc}*/
    @Override
    public int getTouchX(final int pointer)
    {
        return touchHandler.getX(pointer);
    }

    /**{@inheritDoc}*/
    @Override
    public int getTouchY(final int pointer)
    {
        return touchHandler.getY(pointer);
    }

    /**{@inheritDoc}*/
    @Override
    public float getAccelX()
    {
        return accelHandler.getX();
    }

    /**{@inheritDoc}*/
    @Override
    public float getAccelY()
    {
        return accelHandler.getY();
    }

    /**{@inheritDoc}*/
    @Override
    public float getAccelZ()
    {
        return accelHandler.getZ();
    }

    /**{@inheritDoc}*/
    @Override
    public void setBlocked(final boolean blocked)
    {
        touchHandler.setBlocked(blocked);
        keyHandler.setBlocked(blocked);
        accelHandler.setBlocked(blocked);
    }

    /**{@inheritDoc}*/
    @Override
    public void vibrate(final long timeout)
    {
        if ((null != vibrator) && context.getAttributeAsBoolean(Constants.ATTRIBUTE_VIBRATION_ENABLED))
        {
            vibrator.vibrate(timeout);
        }
    }
}
