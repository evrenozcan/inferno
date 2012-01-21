/*
 * 
 */
package net.cosmologic.inferno.android.handler;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

/**
 * The Class AccelerometerHandler.
 * 
 * @author Evren Ozcan
 */
public class AccelerometerHandler implements SensorEventListener, IHandler
{
    
    /** The blocked. */
    private volatile boolean blocked;
    
    /** The x. */
    float x;
    
    /** The y. */
    float y;
    
    /** The z. */
    float z;

    /**
     * Instantiates a new accelerometer handler.
     *
     * @param context the context
     */
    public AccelerometerHandler(final Context context)
    {
        final SensorManager manager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        if (!manager.getSensorList(Sensor.TYPE_ACCELEROMETER).isEmpty())
        {
            final Sensor acceleroMeter = manager.getSensorList(Sensor.TYPE_ACCELEROMETER).get(0);
            manager.registerListener(this, acceleroMeter, SensorManager.SENSOR_DELAY_GAME);
        }
    }

    /**{@inheritDoc}*/
    @Override
    public void onAccuracyChanged(final Sensor sensor, final int accuracy)
    {
        if (blocked)
        {
            return;
        }
    }

    /**{@inheritDoc}*/
    @Override
    public void onSensorChanged(final SensorEvent event)
    {
        if (blocked)
        {
            return;
        }
        x = event.values[0];
        y = event.values[1];
        z = event.values[2];
    }

    /**
     * Gets the x.
     *
     * @return the x
     */
    public float getX()
    {
        return x;
    }

    /**
     * Gets the y.
     *
     * @return the y
     */
    public float getY()
    {
        return y;
    }

    /**
     * Gets the z.
     *
     * @return the z
     */
    public float getZ()
    {
        return z;
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
