/*
 * 
 */
package net.cosmologic.inferno.ui.screen.impl;

import java.util.ArrayList;
import java.util.List;

import net.cosmologic.inferno.IContext;
import net.cosmologic.inferno.InfernoException;
import net.cosmologic.inferno.android.util.InfernoLogger;
import net.cosmologic.inferno.event.IGameEvent;
import net.cosmologic.inferno.event.IGameEvent.EventType;
import net.cosmologic.inferno.io.TouchEventWrapper;
import net.cosmologic.inferno.ui.graphics.IBitmapWrapper.BitmapFormat;
import net.cosmologic.inferno.ui.graphics.IGraphics;
import net.cosmologic.inferno.ui.screen.IComponent;
import net.cosmologic.inferno.ui.screen.ILayer;
import net.cosmologic.inferno.ui.screen.INavigator;
import net.cosmologic.inferno.util.Pool;
import net.cosmologic.inferno.util.StringUtils;

/**
 * The Class Layer.
 * 
 * @author Evren Ozcan
 */
public abstract class Layer extends Container implements ILayer
{
    
    /** The components. */
    private final List<IComponent> components = new ArrayList<IComponent>();
    
    /** The component to add. */
    private final Pool<IComponent> componentToAdd = new Pool<IComponent>(100);
    
    /** The component to remove. */
    private Pool<IComponent> componentToRemove;
    
    /** The action navigator. */
    private ActionNavigator actionNavigator;
    
    /** The request focus. */
    private boolean requestFocus = false;

    /**
     * Instantiates a new layer.
     *
     * @param context the context
     * @param componentId the component id
     * @param bounds the bounds
     * @param transparent the transparent
     */
    protected Layer(final IContext context, final String componentId, final Bounds bounds, final boolean transparent)
    {
        super(context, componentId, bounds, transparent);
    }

    /**{@inheritDoc}*/
    @Override
    public void update(final float delta)
    {
        processPendingComponentQueue();
        final IComponent bg = getBackground();
        if (null != bg)
        {
            bg.update(delta);
        }
        super.update(delta);

        for (final IComponent item : components)
        {
            item.update(delta);
        }
        final IComponent fg = getForeground();
        if (null != fg)
        {
            fg.update(delta);
        }
    }

    /**
     * Process pending component queue.
     */
    protected void processPendingComponentQueue()
    {
        while (componentToAdd.hasPending())
        {
            final IComponent component = componentToAdd.poll();
            components.add(component);
            if (component.isAction())
            {
                if (null == actionNavigator)
                {
                    this.actionNavigator = new ActionNavigator(this, requestFocus);
                }
            }
        }
        if (null != componentToRemove)
        {
            while (componentToRemove.hasPending())
            {
                final IComponent component = componentToRemove.poll();
                if (components.remove(component))
                {
                    if (null != actionNavigator)
                    {
                        actionNavigator.reset();
                    }
                    component.dispose();
                }
            }
        }
    }

    /**{@inheritDoc}*/
    @Override
    public void render(final IGraphics graphics, final float delta)
    {
        if (getBitmap().getFormat() == BitmapFormat.ARGB8888)
        {
            containerLocal.clearTransparent();
        }
        else
        {
            containerLocal.clear();
        }
        renderBg(delta);
        preContentDraw(graphics, containerLocal, delta);
        renderContent(delta);
        postContentDraw(graphics, containerLocal, delta);
        renderFg(delta);
        polishContentDraw(graphics, containerLocal, delta);
        super.render(graphics, delta);
    }

    /**
     * Pre content draw.
     *
     * @param parent the parent
     * @param containerLocal the container local
     * @param delta the delta
     */
    public void preContentDraw(final IGraphics parent, final IGraphics containerLocal, final float delta)
    {

    }

    /**
     * Post content draw.
     *
     * @param parent the parent
     * @param containerLocal the container local
     * @param delta the delta
     */
    public void postContentDraw(final IGraphics parent, final IGraphics containerLocal, final float delta)
    {

    }

    /**
     * Polish content draw.
     *
     * @param parent the parent
     * @param containerLocal the container local
     * @param delta the delta
     */
    public void polishContentDraw(final IGraphics parent, final IGraphics containerLocal, final float delta)
    {

    }

    /**
     * Render bg.
     *
     * @param delta the delta
     */
    private void renderBg(final float delta)
    {
        final IComponent bg = getBackground();
        if ((null != bg) && bg.isVisible())
        {
            bg.render(containerLocal, delta);
        }
    }

    /**
     * Render content.
     *
     * @param delta the delta
     */
    private void renderContent(final float delta)
    {
        for (final IComponent item : components)
        {
            if (item.isVisible())
            {
                item.render(containerLocal, delta);
            }
        }
    }

    /**
     * Render fg.
     *
     * @param delta the delta
     */
    private void renderFg(final float delta)
    {
        final IComponent fg = getForeground();
        if ((null != fg) && fg.isVisible())
        {
            fg.render(containerLocal, delta);
        }
    }

    /**
     * Request focus.
     */
    public void requestFocus()
    {
        requestFocus = true;
    }

    /**{@inheritDoc}*/
    @Override
    public boolean onInterrupt()
    {
        Layer topLayer = getTopLayer();
        if (null != topLayer)
        {
            Layer kingOfTheKings = topLayer.getTopLayer();
            while (kingOfTheKings != null)
            {
                topLayer = kingOfTheKings;
                kingOfTheKings = kingOfTheKings.getTopLayer();
            }
        }
        if (null != topLayer)
        {
            return topLayer.onInterrupt();
        }
        else
        {
            return false;
        }
    }

    /**
     * Gets the top layer.
     *
     * @return the top layer
     */
    protected Layer getTopLayer()
    {
        if (components.isEmpty())
        {
            return null;
        }
        else
        {
            for (int i = components.size() - 1; i >= 0; i--)
            {
                final IComponent component = components.get(i);
                if ((component instanceof Layer) && component.isVisible())
                {
                    return (Layer) component;
                }
            }
        }

        return null;
    }

    /**{@inheritDoc}*/
    @Override
    public boolean onTouchEvent(final IGameEvent touchEvent)
    {
        boolean found = false;
        final TouchEventWrapper wrapper = (TouchEventWrapper) touchEvent.getData();
        final Layer layer = getTopLayer();
        InfernoLogger.debug("layer", "Touched layer" + layer);
        if ((null != layer) && !layer.isExternalInputBlocked())
        {
            InfernoLogger.debug("layer", "sending event to.");
            if (layer.isInBounds(wrapper.x, wrapper.y))
            {
                InfernoLogger.debug("layer", "inbounds");
                if (!layer.onTouchEvent(touchEvent))
                {
                    if ((null != getForeground()) && !getForeground().isExternalInputBlocked())
                    {
                        if (getForeground().isInBounds(wrapper.x, wrapper.y))
                        {
                            found = getForeground().onTouchEvent(touchEvent);
                        }
                    }
                    return found;
                }
                else
                {
                    return true;
                }
            }
            else
            {
                if (touchEvent.getAction() == EventType.Action.TOUCH_UP)
                {
                    layer.onInterrupt();
                }
            }
        }

        if (!found && (null != actionNavigator))
        {
            if (touchEvent.getAction() == EventType.Action.TOUCH_UP)
            {
                actionNavigator.reset();
            }
        }

        for (final IComponent other : components)
        {
            if (other == layer)
            {
                continue;
            }
            if (other.isInBounds(wrapper.x, wrapper.y) && !other.isExternalInputBlocked())
            {
                final boolean result = other.onTouchEvent(touchEvent);
                if (other.isAction())
                {
                    actionNavigator.setCurrentAction(other);
                    return result;
                }
            }
        }

        if ((null != getForeground()) && !getForeground().isExternalInputBlocked())
        {
            if (getForeground().isInBounds(wrapper.x, wrapper.y))
            {
                found = getForeground().onTouchEvent(touchEvent);
            }
        }
        if (!found && (null != getBackground()) && !getBackground().isExternalInputBlocked())
        {
            found = getBackground().onTouchEvent(touchEvent);
        }

        return found;
    }

    /**{@inheritDoc}*/
    @Override
    public boolean onKeyEvent(final IGameEvent keyEvent)
    {
        final Layer layer = getTopLayer();
        if ((null != layer) && !layer.isExternalInputBlocked())
        {
            return layer.onKeyEvent(keyEvent);
        }
        else
        {
            return false;
        }
    }

    /**
     * Gets the components.
     *
     * @return the components
     */
    public List<IComponent> getComponents()
    {
        return components;
    }

    /**{@inheritDoc}*/
    @Override
    public void removeComponent(final IComponent component)
    {
        if (null == componentToRemove)
        {
            componentToRemove = new Pool<IComponent>(100);
        }
        if (component.isAction())
        {
            component.setVisible(false);
            if ((null != actionNavigator) && (component == actionNavigator.getCurrentAction()))
            {
                actionNavigator.reset();
            }
        }
        componentToRemove.offerQuietely(component);
    }

    /**{@inheritDoc}*/
    @Override
    public void addComponent(final IComponent component, final boolean asynch)
    {
        if (componentToAdd.getObjects().contains(component))
        {
            return;
        }

        if (isComponentExist(component.getId()))
        {
            throw new InfernoException( "The id is not unique, there is another component with id " + component.getId());
        }
        if (asynch)
        {
            componentToAdd.offerQuietely(component);
        }
        else
        {
            components.add(component);
            if (component.isAction())
            {
                if (null == actionNavigator)
                {
                    this.actionNavigator = new ActionNavigator(this, requestFocus);
                }
            }
        }

        component.setParent(this);
    }

    /**
     * Checks if is component exist.
     *
     * @param componentId the component id
     * @return true, if is component exist
     */
    public boolean isComponentExist(final String componentId)
    {
        try
        {
            getComponent(componentId);
            return true;
        }
        catch (final InfernoException e)
        {
            return false;
        }
    }

    /**{@inheritDoc}*/
    @Override
    public IComponent getComponent(final String componentId)
    {
        if (StringUtils.isBlank(componentId))
        {
            throw new InfernoException("Invalid componentId");
        }
        for (final IComponent component : components)
        {
            if (StringUtils.equals(componentId, component.getId()))
            {
                return component;
            }
        }
        final IComponent bg = getBackground();

        if (null != bg)
        {
            if (StringUtils.equals(componentId, bg.getId()))
            {
                return bg;
            }
            else if (bg instanceof Container)
            {
                return ((Container) bg).getComponent(componentId);
            }
        }

        final IComponent fg = getForeground();
        if (null != fg)
        {
            if (StringUtils.equals(componentId, fg.getId()))
            {
                return fg;
            }
            else if (fg instanceof Container)
            {
                return ((Container) fg).getComponent(componentId);
            }
        }
        throw new InfernoException(componentId + " does not exist");
    }

    /**{@inheritDoc}*/
    @Override
    public void processInput(final IGameEvent event)
    {
        if (event.getType() == EventType.TOUCH)
        {
            onTouchEvent(event);
        }
        else if (event.getType() == EventType.KEYPRESS)
        {
            onKeyEvent(event);
        }
        else
        {
            final Layer top = getTopLayer();
            if (null != top)
            {
                top.processInput(event);
            }
        }
    }

    /**
     * Gets the background.
     *
     * @return the background
     */
    public IComponent getBackground()
    {
        return null;
    }

    /**
     * Gets the foreground.
     *
     * @return the foreground
     */
    public IComponent getForeground()
    {
        return null;
    }

    /**{@inheritDoc}*/
    @Override
    public int getWidth()
    {
        return bounds.width;
    }

    /**{@inheritDoc}*/
    @Override
    public int getHeight()
    {
        return bounds.height;
    }

    /**{@inheritDoc}*/
    @Override
    public INavigator getActionNavigator()
    {
        return actionNavigator;
    }

    /**{@inheritDoc}*/
    @Override
    public void onPause()
    {
    }

    /**{@inheritDoc}*/
    @Override
    public void onResume()
    {
    }

    /**{@inheritDoc}*/
    @Override
    public void dispose()
    {
        if (null != getForeground())
        {
            getForeground().dispose();
        }
        for (final IComponent component : components)
        {
            component.dispose();
        }
        if (null != getBackground())
        {
            getBackground().dispose();
        }

        components.clear();
        super.dispose();
    }
}
