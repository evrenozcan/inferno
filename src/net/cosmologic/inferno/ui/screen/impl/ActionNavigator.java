/*
 * 
 */
package net.cosmologic.inferno.ui.screen.impl;

import net.cosmologic.inferno.ui.screen.IComponent;
import net.cosmologic.inferno.ui.screen.INavigator;
import net.cosmologic.inferno.util.NumberUtils;

/**
 * The Class ActionNavigator.
 * 
 * @author Evren Ozcan
 */
public class ActionNavigator implements INavigator
{

    /** The layer. */
    private final Layer layer;
    
    /** The cursor. */
    private int cursor = NumberUtils.UNASSIGNED_ID;

    /**
     * Instantiates a new action navigator.
     *
     * @param layer the layer
     * @param requestFocus the request focus
     */
    public ActionNavigator(final Layer layer, final boolean requestFocus)
    {
        this.layer = layer;
        if (requestFocus && (null != layer.getComponents()))
        {
            for (final IComponent component : layer.getComponents())
            {
                // move cursor to first action.
                if (component.isAction())
                {
                    setCurrentAction(component);
                    break;
                }
            }
        }
    }

    /**
     * Move cursor.
     *
     * @param cursor the cursor
     */
    private void moveCursor(final int cursor)
    {
        this.cursor = cursor;
    }

    /**
     * Update cursor.
     *
     * @param component the component
     */
    protected void updateCursor(final IComponent component)
    {
        if (cursor < 0)
        {
            // move cursor to first action.
            if (component.isAction())
            {
                setCurrentAction(component);
            }
        }
    }

    /**{@inheritDoc}*/
    @Override
    public void setCurrentAction(final IComponent component)
    {
        moveCursor(layer.getComponents().indexOf(component));

    }

    /**{@inheritDoc}*/
    @Override
    public IComponent getCurrentAction()
    {
        if (cursor >= 0)
        {
            return layer.getComponents().get(cursor);
        }
        else
        {
            return null;
        }
    }

    /**{@inheritDoc}*/
    @Override
    public IComponent getAction(final int eventX, final int eventY)
    {
        for (final IComponent component : layer.getComponents())
        {
            if (component.isAction() && component.isInBounds(eventX, eventY))
            {
                final IComponent action = component;
                return action;
            }
        }
        return null;
    }

    /**{@inheritDoc}*/
    @Override
    public void navigateDown()
    {
        int internalCursor = cursor + 1;
        final int componentCount = layer.getComponents().size();
        if (internalCursor == componentCount)
        {
            internalCursor = 0;
        }
        int totalLoop = 0;
        for (int i = internalCursor; i < layer.getComponents().size(); i++)
        {
            if (totalLoop++ > layer.getComponents().size())
            {
                break;
            }
            final IComponent component = layer.getComponents().get(i);
            if (component.isAction())
            {
                moveCursor(i);
                break;
            }

            if (i == internalCursor - 1)
            {
                reset();
                break;
            }
            if (i == componentCount - 1)
            {
                i = NumberUtils.UNASSIGNED_ID;
            }
        }
    }

    /**{@inheritDoc}*/
    @Override
    public void navigateUp()
    {
        int internalCursor = cursor - 1;
        final int componentCount = layer.getComponents().size();
        if (internalCursor < 0)
        {
            internalCursor = componentCount - 1;
        }

        int totalLoop = 0;
        for (int i = internalCursor; i >= 0; i--)
        {
            if (totalLoop++ > layer.getComponents().size())
            {
                break;
            }
            final IComponent component = layer.getComponents().get(i);
            if (component.isAction())
            {
                moveCursor(i);
                break;
            }
            if (i == internalCursor + 1)
            {
                reset();
                break;
            }
            if (i == 0)
            {
                i = componentCount;
            }
        }
    }

    /**{@inheritDoc}*/
    @Override
    public void reset()
    {
        moveCursor(NumberUtils.UNASSIGNED_ID);
    }
}
