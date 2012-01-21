/*
 * 
 */
package net.cosmologic.inferno.android.ui.layer;

import net.cosmologic.agelaos.ui.UIConstants;
import net.cosmologic.inferno.IContext;
import net.cosmologic.inferno.android.InfernoAndroidContext;
import net.cosmologic.inferno.android.ui.graphics.AndroidGraphics;
import net.cosmologic.inferno.event.IGameEvent;
import net.cosmologic.inferno.event.IGameEvent.EventType;
import net.cosmologic.inferno.io.KeyEventWrapper;
import net.cosmologic.inferno.ui.graphics.IBitmapWrapper.BitmapFormat;
import net.cosmologic.inferno.ui.graphics.IGraphics;
import net.cosmologic.inferno.ui.screen.IComponent;
import net.cosmologic.inferno.ui.screen.IContainer;
import net.cosmologic.inferno.ui.screen.impl.Layer;
import android.view.KeyEvent;

/**
 * The Class AndroidLayer.
 * 
 * @author Evren Ozcan
 */
public class AndroidLayer extends Layer implements UIConstants
{

    /**
     * Instantiates a new android layer.
     *
     * @param parent the parent
     * @param componentId the component id
     */
    public AndroidLayer(final IContainer parent, final String componentId)
    {
        this(parent, componentId, new Bounds(0, 0, parent.getWidth(), parent.getHeight()), false);
    }

    /**
     * Instantiates a new android layer.
     *
     * @param parent the parent
     * @param componentId the component id
     * @param transparent the transparent
     */
    public AndroidLayer(final IContainer parent, final String componentId, final boolean transparent)
    {
        this(parent, componentId, new Bounds(0, 0, parent.getWidth(), parent.getHeight()), transparent);
    }

    /**
     * Instantiates a new android layer.
     *
     * @param parent the parent
     * @param componentId the component id
     * @param bounds the bounds
     * @param transparent the transparent
     */
    public AndroidLayer(final IContainer parent, final String componentId, final Bounds bounds,
            final boolean transparent)
    {
        super(parent.getContext(), componentId, bounds, transparent);
        setParent(parent);
    }

    /**
     * Instantiates a new android layer.
     *
     * @param parent the parent
     * @param componentId the component id
     * @param bounds the bounds
     */
    public AndroidLayer(final IContainer parent, final String componentId, final Bounds bounds)
    {
        this(parent, componentId, bounds, false);
    }

    /**{@inheritDoc}*/
    @Override
    protected IGraphics createGraphics(final IContext context, final int width, final int height,
            final BitmapFormat format)
    {
        return new AndroidGraphics(context, "graphics-" + getId(), width, height, format);
    }

    /**{@inheritDoc}*/
    @Override
    public InfernoAndroidContext getContext()
    {
        return (InfernoAndroidContext) super.getContext();
    }

    /**{@inheritDoc}*/
    @Override
    public boolean onKeyEvent(final IGameEvent keyEvent)
    {
        final Layer layer = getTopLayer();
        if (null != layer)
        {
            return layer.onKeyEvent(keyEvent);
        }
        if (null == getActionNavigator())
        {
            return false;
        }

        final KeyEventWrapper wrapper = (KeyEventWrapper) keyEvent.getData();
        if (keyEvent.getAction() == EventType.Action.KEY_DOWN)
        {
            if ((wrapper.keyCode == KeyEvent.KEYCODE_2) || (wrapper.keyCode == KeyEvent.KEYCODE_DPAD_UP)
                    || (wrapper.keyCode == KeyEvent.KEYCODE_DPAD_LEFT))
            {
                getActionNavigator().navigateUp();
            }
            else if ((wrapper.keyCode == KeyEvent.KEYCODE_8) || (wrapper.keyCode == KeyEvent.KEYCODE_DPAD_DOWN)
                    || (wrapper.keyCode == KeyEvent.KEYCODE_DPAD_RIGHT))
            {
                getActionNavigator().navigateDown();
            }
        }
        else if (keyEvent.getAction() == EventType.Action.KEY_UP)
        {
            final IComponent currentAction = getActionNavigator().getCurrentAction();
            if (null != currentAction)
            {
                return currentAction.onKeyEvent(keyEvent);
            }
        }
        return true;
    }
}
