/*
 * 
 */
package net.cosmologic.inferno.android.reactor;

import net.cosmologic.inferno.android.InfernoAndroidActivity2D;
import android.app.Activity;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

/**
 * The Class AndroidSurfaceView.
 * 
 * @author Evren Ozcan
 */
public class AndroidSurfaceView extends SurfaceView implements Callback
{
    
    /**
     * Instantiates a new android surface view.
     *
     * @param activity the activity
     */
    public AndroidSurfaceView(final Activity activity)
    {
        super(activity);
    }

    /**{@inheritDoc}*/
    @Override
    public void surfaceChanged(final SurfaceHolder holder, final int format, final int width, final int height)
    {
    }

    /**{@inheritDoc}*/
    @Override
    public void surfaceCreated(final SurfaceHolder holder)
    {
        ((InfernoAndroidActivity2D) getContext()).onSurfaceReady();
    }

    /**{@inheritDoc}*/
    @Override
    public void surfaceDestroyed(final SurfaceHolder holder)
    {
    }
}
