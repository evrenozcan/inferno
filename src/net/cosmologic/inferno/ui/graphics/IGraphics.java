/*
 * 
 */
package net.cosmologic.inferno.ui.graphics;

import net.cosmologic.inferno.animation.InfernoAnimation;
import net.cosmologic.inferno.ui.screen.IComponent;

/**
 * The Interface IGraphics.
 * 
 * @author Evren Ozcan
 */
public interface IGraphics
{
    
    /**
     * Clear.
     */
    void clear();

    /**
     * Clear.
     *
     * @param color the color
     */
    void clear(int color);

    /**
     * Clear transparent.
     */
    void clearTransparent();

    /**
     * Gets the width.
     *
     * @return the width
     */
    int getWidth();

    /**
     * Gets the height.
     *
     * @return the height
     */
    int getHeight();

    /**
     * Draw pixel.
     *
     * @param x the x
     * @param y the y
     * @param color the color
     */
    void drawPixel(int x, int y, int color);

    /**
     * Draw line.
     *
     * @param x the x
     * @param y the y
     * @param x2 the x2
     * @param y2 the y2
     * @param color the color
     */
    void drawLine(int x, int y, int x2, int y2, int color);

    /**
     * Draw rect.
     *
     * @param x the x
     * @param y the y
     * @param width the width
     * @param height the height
     * @param color the color
     */
    void drawRect(int x, int y, int width, int height, int color);

    /**
     * Draw rect.
     *
     * @param x the x
     * @param y the y
     * @param width the width
     * @param height the height
     * @param color the color
     * @param alpha the alpha
     */
    void drawRect(int x, int y, int width, int height, int color, int alpha);

    /**
     * Fill rect.
     *
     * @param x the x
     * @param y the y
     * @param width the width
     * @param height the height
     * @param color the color
     */
    void fillRect(int x, int y, int width, int height, int color);

    /**
     * Fill rect.
     *
     * @param x the x
     * @param y the y
     * @param width the width
     * @param height the height
     * @param color the color
     * @param alpha the alpha
     */
    void fillRect(int x, int y, int width, int height, int color, int alpha);

    /**
     * Draw round rect.
     *
     * @param component the component
     * @param x the x
     * @param y the y
     * @param color the color
     * @param alpha the alpha
     */
    void drawRoundRect(IComponent component, int x, int y, int color, int alpha);

    /**
     * Fill round rect.
     *
     * @param component the component
     * @param x the x
     * @param y the y
     * @param color the color
     * @param alpha the alpha
     */
    void fillRoundRect(IComponent component, int x, int y, int color, int alpha);

    /**
     * Draw circle.
     *
     * @param cx the cx
     * @param cy the cy
     * @param radius the radius
     * @param color the color
     */
    void drawCircle(int cx, int cy, int radius, int color);

    /**
     * Draw text.
     *
     * @param text the text
     * @param x the x
     * @param y the y
     * @param color the color
     */
    void drawText(String text, int x, int y, int color);

    /**
     * Draw text.
     *
     * @param text the text
     * @param x the x
     * @param y the y
     * @param color the color
     * @param size the size
     */
    void drawText(String text, int x, int y, int color, int size);

    /**
     * Draw anitmation.
     *
     * @param animation the animation
     */
    void drawAnitmation(InfernoAnimation animation);

    /**
     * Draw component.
     *
     * @param component the component
     */
    void drawComponent(IComponent component);

    /**
     * Draw component.
     *
     * @param component the component
     * @param alpha the alpha
     */
    void drawComponent(IComponent component, int alpha);

    /**
     * Gets the frame buffer.
     *
     * @return the frame buffer
     */
    IBitmapWrapper getFrameBuffer();

    /**
     * Gets the text width.
     *
     * @param text the text
     * @param size the size
     * @return the text width
     */
    int getTextWidth(String text, float size);

    /**
     * Draw bitmap.
     *
     * @param wrapper the wrapper
     * @param x the x
     * @param y the y
     */
    void drawBitmap(IBitmapWrapper wrapper, int x, int y);
}
