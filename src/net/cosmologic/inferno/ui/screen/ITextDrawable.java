/*
 * 
 */
package net.cosmologic.inferno.ui.screen;

/**
 * The Interface ITextDrawable.
 * 
 * @author Evren Ozcan
 */
public interface ITextDrawable extends IBitmapDrawable
{
    
    /**
     * Gets the text.
     *
     * @return the text
     */
    String getText();

    /**
     * Gets the text size.
     *
     * @return the text size
     */
    int getTextSize();

    /**
     * Gets the text color.
     *
     * @return the text color
     */
    int getTextColor();

    /**
     * Gets the margins.
     *
     * @return the margins
     */
    Margins getMargins();

    /**
     * Sets the text size.
     *
     * @param textSize the new text size
     */
    void setTextSize(int textSize);

    /**
     * Sets the text color.
     *
     * @param textColor the new text color
     */
    void setTextColor(int textColor);

    /**
     * Sets the text.
     *
     * @param text the new text
     */
    void setText(String text);

    /**
     * Sets the margins.
     *
     * @param margins the new margins
     */
    void setMargins(Margins margins);

    /**
     * The Class Margins.
     */
    public class Margins
    {
        
        /** The DEFAUL t_ instance. */
        public static Margins DEFAULT_INSTANCE = new Margins(0, 0, 2);
        
        /** The ACTIO n_ instance. */
        public static Margins ACTION_INSTANCE = new Margins(12, 12, 4);
        
        /** The left. */
        public int left = 0;
        
        /** The top. */
        public int top = 0;
        
        /** The spacing. */
        public int spacing = 2;

        /**
         * Instantiates a new margins.
         *
         * @param left the left
         * @param top the top
         * @param spacing the spacing
         */
        public Margins(final int left, final int top, final int spacing)
        {
            this.left = left;
            this.top = top;
            this.spacing = spacing;
        }
    }
}
