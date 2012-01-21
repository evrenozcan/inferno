/*
 * 
 */
package net.cosmologic.inferno.ui.screen.impl;

import net.cosmologic.inferno.IContext;
import net.cosmologic.inferno.InfernoException;
import net.cosmologic.inferno.event.IGameEvent;
import net.cosmologic.inferno.event.IGameEvent.EventType.Action;
import net.cosmologic.inferno.ui.screen.IComponent;
import net.cosmologic.inferno.ui.screen.IGamePlatform;
import net.cosmologic.inferno.ui.screen.ILayer;
import net.cosmologic.inferno.util.StringUtils;

/**
 * The Class GamePlatform.
 * 
 * @author Evren Ozcan
 */
public abstract class GamePlatform extends Container implements IGamePlatform
{

    /** The display. */
    private ILayer display;

    /**
     * Instantiates a new game platform.
     *
     * @param context the context
     * @param id the id
     * @param bounds the bounds
     * @param transparent the transparent
     */
    protected GamePlatform(final IContext context, final String id, final Bounds bounds, final boolean transparent)
    {
        super(context, id, bounds, transparent);
    }

    /**{@inheritDoc}*/
    @Override
    public void onPause()
    {
        getContext().getGameInput().setBlocked(false);
        if (null != display)
        {
            display.onPause();
        }
        getContext().getTribune().turnOff();
    }

    /**{@inheritDoc}*/
    @Override
    public void onFinish()
    {
        if (null != display)
        {
            display.dispose();
        }
        dispose();
    }

    /**{@inheritDoc}*/
    @Override
    public void onResume()
    {
        getContext().getTribune().start();
        if (null != getDisplay())
        {
            getDisplay().onResume();
        }
    }

    /**{@inheritDoc}*/
    @Override
    public void processInput(final IGameEvent event)
    {
        if (event.getAction() == Action.SWITCH_DISPLAY)
        {
            setDisplay((ILayer) event.getData());
        }
        else
        {
            final ILayer bg = getBackground();
            if ((null != bg) && bg.isVisible())
            {
                bg.processInput(event);
            }
            display.processInput(event);
            final ILayer fg = getForeground();
            if ((null != fg) && fg.isVisible())
            {
                fg.processInput(event);
            }
        }
    }

    /**{@inheritDoc}*/
    @Override
    public void refreshFrame(final float delta)
    {
        if (null == display)
        {
            getContext().getTribune().turnOff();
            throw new InfernoException("There is no screen set for this game");
        }
        containerLocal.clear();
        final ILayer bg = getBackground();
        if ((null != bg) && bg.isVisible())
        {
            bg.update(delta);
            bg.render(containerLocal, delta);
        }
        display.update(delta);
        if (display.isVisible())
        {
            display.render(containerLocal, delta);
        }
        final ILayer fg = getForeground();
        if ((null != fg) && fg.isVisible())
        {
            fg.update(delta);
            fg.render(containerLocal, delta);
        }
    }

    /**{@inheritDoc}*/
    @Override
    public ILayer getDisplay()
    {
        if (null == display)
        {
            display = getFirstDisplay();
        }
        return display;
    }

    /**{@inheritDoc}*/
    @Override
    public IComponent getComponent(final String componentId)
    {
        if (null != getBackground())
        {
            if (StringUtils.equals(componentId, getBackground().getId()))
            {
                return getBackground();
            }
        }

        if (null != display)
        {
            if (StringUtils.equals(componentId, display.getId()))
            {
                return display;
            }
        }

        if (null != getForeground())
        {
            if (StringUtils.equals(componentId, getForeground().getId()))
            {
                return getForeground();
            }
        }

        return null;
    }

    /**
     * Sets the display.
     *
     * @param display the new display
     */
    private void setDisplay(final ILayer display)
    {
        if ((display == null) || (display == this.display))
        {
            throw new InfernoException("Screen must not be null nor must not be same as current");
        }
        if (null != this.display)
        {
            this.display.dispose();
        }
        this.display = display;
    }

    /**
     * Gets the background.
     *
     * @return the background
     */
    protected ILayer getBackground()
    {
        return null;
    }

    /**
     * Gets the foreground.
     *
     * @return the foreground
     */
    protected ILayer getForeground()
    {
        return null;
    }

    /**
     * Gets the first display.
     *
     * @return the first display
     */
    protected abstract ILayer getFirstDisplay();
}
