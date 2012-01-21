/*
 * 
 */
package net.cosmologic.inferno.android;

import net.cosmologic.inferno.android.reactor.AndroidSurfaceView;
import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.view.Window;
import android.view.WindowManager;

/**
 * The Class InfernoAndroidActivity2D.
 * 
 * @author Evren Ozcan
 */
public abstract class InfernoAndroidActivity2D extends Activity
{
    
    /** The wake lock. */
    private WakeLock wakeLock;
    
    /** The view. */
    private AndroidSurfaceView view;
    
    /** The context. */
    private InfernoAndroidContext context;

    /**{@inheritDoc}*/
    @Override
    protected final void onCreate(final Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        view = new AndroidSurfaceView(this);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        final PowerManager powerManager = (PowerManager) getSystemService(Context.POWER_SERVICE);
        wakeLock = powerManager.newWakeLock(PowerManager.FULL_WAKE_LOCK, "Cosmologic");
        final int frameBufferWidth = isLandscape() ? 480 : 320;
        final int frameBufferHeight = isLandscape() ? 320 : 480;
        context = new InfernoAndroidContext(this);
        context.setPlatform(createGamePlatform(context, frameBufferWidth, frameBufferHeight));
        setContentView(view);
    }

    /**
     * Creates the game platform.
     *
     * @param context the context
     * @param width the width
     * @param height the height
     * @return the android game platform
     */
    protected abstract AndroidGamePlatform createGamePlatform(InfernoAndroidContext context, int width, int height);

    /**
     * Gets the view.
     *
     * @return the view
     */
    public AndroidSurfaceView getView()
    {
        return view;
    }

    /**
     * Checks if is landscape.
     *
     * @return true, if is landscape
     */
    public final boolean isLandscape()
    {
        return getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
    }

    /**
     * Gets the scale x.
     *
     * @return the scale x
     */
    protected final float getScaleX()
    {
        final int frameBufferWidth = isLandscape() ? 480 : 320;
        return (float) frameBufferWidth / getWindowManager().getDefaultDisplay().getWidth();
    }

    /**
     * Gets the scale y.
     *
     * @return the scale y
     */
    protected float getScaleY()
    {
        final int frameBufferHeight = isLandscape() ? 320 : 480;
        return (float) frameBufferHeight / getWindowManager().getDefaultDisplay().getHeight();
    }

    /**{@inheritDoc}*/
    @Override
    protected void onResume()
    {
        super.onResume();
        wakeLock.acquire();
        if (null != context.getPlatform())
        {
            context.getPlatform().onResume();
        }
    }

    /**{@inheritDoc}*/
    @Override
    protected void onPause()
    {
        super.onPause();
        wakeLock.release();
        if (null != context.getPlatform())
        {
            context.getPlatform().onPause();
            if (isFinishing())
            {
                context.getPlatform().onFinish();
            }
        }
    }

    /**
     * On surface ready.
     */
    public void onSurfaceReady()
    {
    }
}
