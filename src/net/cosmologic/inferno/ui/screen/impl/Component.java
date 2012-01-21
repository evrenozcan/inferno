/*
 * 
 */
package net.cosmologic.inferno.ui.screen.impl;

import net.cosmologic.inferno.event.IGameEvent;
import net.cosmologic.inferno.ui.graphics.IGraphics;
import net.cosmologic.inferno.ui.screen.DeltaAttributes;
import net.cosmologic.inferno.ui.screen.IComponent;
import net.cosmologic.inferno.ui.screen.IContainer;
import net.cosmologic.inferno.ui.screen.render.GenericDeltaProcessor;
import net.cosmologic.inferno.ui.screen.render.IDeltaProcessor;

/**
 * The Class Component.
 * 
 * @author Evren Ozcan
 */
public class Component implements IComponent
{
    
    /** The id. */
    private final String id;
    
    /** The visible. */
    private boolean visible = true;
    
    /** The bounds. */
    protected Bounds bounds;
    
    /** The parent. */
    private IContainer parent;
    
    /** The attributes. */
    protected DeltaAttributes attributes;
    
    /** The processor. */
    protected IDeltaProcessor processor;
    
    /** The external input blocked. */
    private boolean externalInputBlocked = false;

    /**{@inheritDoc}*/
    @Override
    public boolean isExternalInputBlocked()
    {
        return externalInputBlocked;
    }

    /**{@inheritDoc}*/
    @Override
    public void setExternalInputBlocked(final boolean externalInputBlocked)
    {
        this.externalInputBlocked = externalInputBlocked;
    }

    /**
     * Instantiates a new component.
     *
     * @param id the id
     */
    protected Component(final String id)
    {
        this(null, id, new Bounds(0, 0, 0, 0));
    }

    /**
     * Instantiates a new component.
     *
     * @param id the id
     * @param bounds the bounds
     */
    protected Component(final String id, final Bounds bounds)
    {
        this(null, id, bounds);
    }

    /**
     * Instantiates a new component.
     *
     * @param parent the parent
     * @param id the id
     */
    protected Component(final IContainer parent, final String id)
    {
        this(parent, id, new Bounds(0, 0, parent.getWidth(), parent.getHeight()));
    }

    /**
     * Instantiates a new component.
     *
     * @param parent the parent
     * @param id the id
     * @param bounds the bounds
     */
    protected Component(final IContainer parent, final String id, final Bounds bounds)
    {
        this.id = id;
        this.bounds = bounds;
        this.parent = parent;
    }

    /**
     * Instantiates a new component.
     *
     * @param parent the parent
     * @param id the id
     * @param x the x
     * @param y the y
     */
    protected Component(final IContainer parent, final String id, final int x, final int y)
    {
        this.id = id;
        this.parent = parent;
        this.bounds = new Bounds(x, y, 0, 0);
    }

    /**{@inheritDoc}*/
    @Override
    public Bounds getBounds()
    {
        return bounds;
    }

    /**{@inheritDoc}*/
    @Override
    public String getId()
    {
        return id;
    }

    /**{@inheritDoc}*/
    @Override
    public boolean isVisible()
    {
        return visible;
    }

    /**{@inheritDoc}*/
    @Override
    public IContainer getParent()
    {
        return parent;
    }

    /**{@inheritDoc}*/
    @Override
    public void update(final float delta)
    {
        if (null == processor)
        {
            GenericDeltaProcessor.INSTANCE.update(this, delta);
        }
        else
        {
            processor.update(this, delta);
        }
    }

    /**{@inheritDoc}*/
    @Override
    public void render(final IGraphics graphics, final float delta)
    {
        if (null == processor)
        {
            GenericDeltaProcessor.INSTANCE.render(graphics, this, delta);
        }
        else
        {
            processor.render(graphics, this, delta);
        }
    }

    /**{@inheritDoc}*/
    @Override
    public void setBounds(final Bounds bounds)
    {
        this.bounds = bounds;
    }

    /**{@inheritDoc}*/
    @Override
    public void setParent(final IContainer component)
    {
        this.parent = component;
    }

    /**{@inheritDoc}*/
    @Override
    public void setVisible(final boolean visible)
    {
        this.visible = visible;
    }

    /**{@inheritDoc}*/
    @Override
    public boolean isInBounds(final int eventX, final int eventY)
    {
        int relativeX = bounds.x;
        int relativeY = bounds.y;
        IComponent parent = getParent();
        while (parent != null)
        {
            relativeX += parent.getBounds().x;
            relativeY += parent.getBounds().y;
            parent = parent.getParent();
        }
        if ((eventX > relativeX) && (eventX < relativeX + bounds.width - 1) && (eventY > relativeY)
                && (eventY < relativeY + bounds.height - 1))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * Center h relative to parent.
     */
    public void centerHRelativeToParent()
    {
        bounds.x = (getParent().getBounds().width - bounds.width) / 2;
    }

    /**
     * Center v relative to parent.
     */
    public void centerVRelativeToParent()
    {
        bounds.y = (getParent().getBounds().height - bounds.height) / 2;
    }

    /**
     * Center relative to.
     *
     * @param component the component
     */
    public void centerRelativeTo(final IComponent component)
    {
        final Bounds source = component.getBounds();
        bounds.x = source.x + source.width / 2 - bounds.width / 2;
        bounds.y = source.y + source.height / 2 - bounds.height / 2;
    }

    /**
     * Center relative to parent.
     */
    public void centerRelativeToParent()
    {
        centerHRelativeToParent();
        centerVRelativeToParent();
    }

    /**{@inheritDoc}*/
    @Override
    public boolean onKeyEvent(final IGameEvent keyEvent)
    {
        return false;
    }

    /**{@inheritDoc}*/
    @Override
    public boolean onTouchEvent(final IGameEvent touchEvent)
    {
        return false;
    }

    /**{@inheritDoc}*/
    @Override
    public boolean isAction()
    {
        return false;
    }

    /**{@inheritDoc}*/
    @Override
    public DeltaAttributes getAttributes()
    {
        return attributes;
    }

    /**{@inheritDoc}*/
    @Override
    public void setAttributes(final DeltaAttributes attributes)
    {
        this.attributes = attributes;
    }

    /**{@inheritDoc}*/
    @Override
    public void setDeltaProcessor(final IDeltaProcessor processor)
    {
        this.processor = processor;
    }

    /**{@inheritDoc}*/
    @Override
    public String toString()
    {
        return "component:" + id + " bounds:" + bounds;
    }

    /**{@inheritDoc}*/
    @Override
    public void setBounds(final int x, final int y, final int w, final int h)
    {
        bounds.x = x;
        bounds.y = y;
        bounds.width = w;
        bounds.height = h;
    }

    /**{@inheritDoc}*/
    @Override
    public void setBounds(final int x, final int y, final int w, final int h, final int srcX, final int srcY)
    {
        setBounds(x, y, w, h);
        bounds.srcX = srcX;
        bounds.srcY = srcY;
    }

    /**{@inheritDoc}*/
    @Override
    public void dispose()
    {
    }
}
