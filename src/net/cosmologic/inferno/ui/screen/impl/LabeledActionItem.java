/*
 * 
 */
package net.cosmologic.inferno.ui.screen.impl;

import net.cosmologic.inferno.sound.ISoundEffect;
import net.cosmologic.inferno.ui.graphics.IBitmapWrapper;
import net.cosmologic.inferno.ui.screen.IContainer;
import net.cosmologic.inferno.ui.screen.ITextDrawable;
import net.cosmologic.inferno.util.ColorUtils;

/**
 * The Class LabeledActionItem.
 * 
 * @author Evren Ozcan
 */
public class LabeledActionItem extends ActionItem implements ITextDrawable
{
    
    /** The label. */
    private String label;
    
    /** The text size. */
    private int textSize;
    
    /** The text color. */
    private int textColor;
    
    /** The margins. */
    protected Margins margins;
    
    /** The icon. */
    private IBitmapWrapper icon;

    /**
     * Instantiates a new labeled action item.
     *
     * @param parent the parent
     * @param id the id
     * @param label the label
     * @param onClick the on click
     */
    public LabeledActionItem(final IContainer parent, final String id, final String label, final ISoundEffect onClick)
    {
        super(parent, id, onClick);
    }

    /**
     * Instantiates a new labeled action item.
     *
     * @param parent the parent
     * @param id the id
     * @param label the label
     * @param onClick the on click
     * @param icon the icon
     */
    public LabeledActionItem(final IContainer parent, final String id, final String label, final ISoundEffect onClick,
            final IBitmapWrapper icon)
    {
        super(parent, id, onClick);
        this.icon = icon;
    }

    /**
     * Instantiates a new labeled action item.
     *
     * @param parent the parent
     * @param id the id
     * @param label the label
     * @param onClick the on click
     * @param icon the icon
     * @param x the x
     * @param y the y
     */
    public LabeledActionItem(final IContainer parent, final String id, final String label, final ISoundEffect onClick,
            final IBitmapWrapper icon, final int x, final int y)
    {
        super(parent, id, onClick, x, y);
        this.icon = icon;
        this.label = label;
        this.textSize = 20;
        this.textColor = ColorUtils.convert(255, 255, 255, 255);
        updateBounds();
    }

    /**
     * Instantiates a new labeled action item.
     *
     * @param parent the parent
     * @param id the id
     * @param label the label
     * @param onClick the on click
     * @param x the x
     * @param y the y
     */
    public LabeledActionItem(final IContainer parent, final String id, final String label, final ISoundEffect onClick,
            final int x, final int y)
    {
        this(parent, id, label, onClick, null, x, y);
    }

    /**
     * Gets the label.
     *
     * @return the label
     */
    public String getLabel()
    {
        return label;
    }

    /**{@inheritDoc}*/
    @Override
    public String getText()
    {
        return label;
    }

    /**{@inheritDoc}*/
    @Override
    public IBitmapWrapper getBitmap()
    {
        return icon;
    }

    /**{@inheritDoc}*/
    @Override
    public int getTextSize()
    {
        return textSize;
    }

    /**{@inheritDoc}*/
    @Override
    public int getTextColor()
    {
        return textColor;
    }

    /**{@inheritDoc}*/
    @Override
    public void setTextSize(final int size)
    {
        this.textSize = size;
        updateBounds();
    }

    /**{@inheritDoc}*/
    @Override
    public void setTextColor(final int color)
    {
        this.textColor = color;
    }

    /**{@inheritDoc}*/
    @Override
    public Margins getMargins()
    {
        if (null == margins)
        {
            return Margins.ACTION_INSTANCE;
        }
        else
        {
            return margins;
        }
    }

    /**{@inheritDoc}*/
    @Override
    public void setMargins(final Margins margins)
    {
        this.margins = margins;
    }

    /**{@inheritDoc}*/
    @Override
    public void setText(final String text)
    {
        this.label = text;
    }

    /**
     * Update bounds.
     */
    private void updateBounds()
    {
        bounds.height = getTextSize() + getMargins().top * 2;
        final int newWidth = getParent().getGraphics().getTextWidth(getText(), getTextSize()) + getMargins().left * 2;
        if (bounds.width < newWidth)
        {
            bounds.width = newWidth;
        }
        if (null != icon)
        {
            bounds.width += icon.getWidth() + getMargins().spacing * 2;
        }
    }
}
