/*
 * 
 */
package net.cosmologic.inferno.ui.screen.impl;

import net.cosmologic.inferno.android.util.InfernoLogger;
import net.cosmologic.inferno.event.IActionListener;
import net.cosmologic.inferno.event.IGameEvent;
import net.cosmologic.inferno.event.IGameEvent.EventType;
import net.cosmologic.inferno.sound.ISoundEffect;
import net.cosmologic.inferno.ui.screen.IActionDrawable;
import net.cosmologic.inferno.ui.screen.IContainer;
import net.cosmologic.inferno.ui.screen.ILayer;

/**
 * The Class ActionItem.
 * 
 * @author Evren Ozcan
 */
public class ActionItem extends Component implements IActionDrawable
{
    
    /** The enabled. */
    private boolean enabled = true;
    
    /** The on click. */
    private final ISoundEffect onClick;
    
    /** The action listener. */
    protected IActionListener actionListener;

    /**
     * Instantiates a new action item.
     *
     * @param parent the parent
     * @param id the id
     * @param onClick the on click
     */
    protected ActionItem(final IContainer parent, final String id, final ISoundEffect onClick)
    {
        super(parent, id);
        this.onClick = onClick;
    }

    /**
     * Instantiates a new action item.
     *
     * @param parent the parent
     * @param id the id
     * @param onClick the on click
     * @param x the x
     * @param y the y
     */
    protected ActionItem(final IContainer parent, final String id, final ISoundEffect onClick, final int x, final int y)
    {
        super(parent, id, x, y);
        this.onClick = onClick;
    }

    /**
     * Instantiates a new action item.
     *
     * @param parent the parent
     * @param id the id
     * @param onClick the on click
     * @param x the x
     * @param y the y
     * @param w the w
     * @param h the h
     */
    protected ActionItem(final IContainer parent, final String id, final ISoundEffect onClick, final int x,
            final int y, final int w, final int h)
    {
        this(parent, id, onClick, x, y);
        bounds.height = h;
        bounds.width = w;
    }

    /**
     * Instantiates a new action item.
     *
     * @param parent the parent
     * @param id the id
     * @param onClick the on click
     * @param x the x
     * @param y the y
     * @param w the w
     * @param h the h
     * @param srcX the src x
     * @param srcY the src y
     */
    protected ActionItem(final IContainer parent, final String id, final ISoundEffect onClick, final int x,
            final int y, final int w, final int h, final int srcX, final int srcY)
    {
        this(parent, id, onClick, x, y, w, h);
        bounds.srcX = srcX;
        bounds.srcY = srcY;
    }

    /**
     * Instantiates a new action item.
     *
     * @param parent the parent
     * @param id the id
     * @param bounds the bounds
     * @param onClick the on click
     */
    protected ActionItem(final IContainer parent, final String id, final Bounds bounds, final ISoundEffect onClick)
    {
        super(parent, id, bounds);
        this.onClick = onClick;
    }

    /**{@inheritDoc}*/
    @Override
    public boolean isAction()
    {
        return isVisible() && isEnabled();
    }

    /**{@inheritDoc}*/
    @Override
    public boolean isEnabled()
    {
        return enabled;
    }

    /**{@inheritDoc}*/
    @Override
    public void setEnabled(final boolean enabled)
    {
        this.enabled = enabled;
    }

    /**{@inheritDoc}*/
    @Override
    public ILayer getParent()
    {
        return (ILayer) super.getParent();
    }

    /**{@inheritDoc}*/
    @Override
    public boolean onKeyEvent(final IGameEvent keyEvent)
    {
        if (!isAction())
        {
            return false;
        }
        InfernoLogger.debug("ActionItem", "key-on:" + this);
        if (null != onClick)
        {
            getParent().getContext().getTribune().queueSoundEffect(onClick);
        }
        onSelect();
        if (null != actionListener)
        {
            actionListener.onAction(this);
        }
        return true;
    }

    /**{@inheritDoc}*/
    @Override
    public boolean onTouchEvent(final IGameEvent touchEvent)
    {
        if (!isAction() || (touchEvent.getAction() != EventType.Action.TOUCH_UP))
        {
            return false;
        }
        if (null != onClick)
        {
            getParent().getContext().getTribune().queueSoundEffect(onClick);
        }
        onSelect();
        InfernoLogger.debug("ActionItem", "touched-on:" + this);
        if (null != actionListener)
        {
            actionListener.onAction(this);
        }
        return true;
    }

    /**{@inheritDoc}*/
    @Override
    public void setActionListener(final IActionListener listener)
    {
        this.actionListener = listener;
    }

    /**
     * On select.
     */
    public void onSelect()
    {

    }
}
