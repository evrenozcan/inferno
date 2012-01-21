/*
 * 
 */
package net.cosmologic.inferno.ui.screen.impl;

import net.cosmologic.inferno.ui.screen.IContainer;

/**
 * The Class LabeledItem.
 * 
 * @author Evren Ozcan
 */
public class LabeledItem extends LabeledActionItem
{

    /**
     * Instantiates a new labeled item.
     *
     * @param parent the parent
     * @param id the id
     * @param label the label
     */
    public LabeledItem(final IContainer parent, final String id, final String label)
    {
        super(parent, id, label, null);
    }

    /**
     * Instantiates a new labeled item.
     *
     * @param parent the parent
     * @param id the id
     * @param label the label
     * @param x the x
     * @param y the y
     */
    public LabeledItem(final IContainer parent, final String id, final String label, final int x, final int y)
    {
        super(parent, id, label, null, x, y);
    }

    /**{@inheritDoc}*/
    @Override
    public Margins getMargins()
    {
        if (null == margins)
        {
            return Margins.DEFAULT_INSTANCE;
        }
        else
        {
            return margins;
        }
    }

    /**{@inheritDoc}*/
    @Override
    public boolean isAction()
    {
        return false;
    }
}
