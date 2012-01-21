/*
 * 
 */
package net.cosmologic.inferno.ui.screen;

import net.cosmologic.inferno.event.IGameEvent;
import net.cosmologic.inferno.ui.graphics.IGraphics;
import net.cosmologic.inferno.ui.screen.render.IDeltaProcessor;

/**
 * The Interface IComponent.
 * 
 * @author Evren Ozcan
 */
public interface IComponent
{
    
    /**
     * Checks if is external input blocked.
     *
     * @return true, if is external input blocked
     */
    boolean isExternalInputBlocked();

    /**
     * Sets the external input blocked.
     *
     * @param block the new external input blocked
     */
    void setExternalInputBlocked(boolean block);

    /**
     * Gets the id.
     *
     * @return the id
     */
    String getId();

    /**
     * Sets the parent.
     *
     * @param component the new parent
     */
    void setParent(IContainer component);

    /**
     * Gets the parent.
     *
     * @return the parent
     */
    IContainer getParent();

    /**
     * Gets the bounds.
     *
     * @return the bounds
     */
    Bounds getBounds();

    /**
     * Sets the bounds.
     *
     * @param bounds the new bounds
     */
    void setBounds(Bounds bounds);

    /**
     * Checks if is in bounds.
     *
     * @param eventX the event x
     * @param eventY the event y
     * @return true, if is in bounds
     */
    boolean isInBounds(int eventX, int eventY);

    /**
     * Checks if is visible.
     *
     * @return true, if is visible
     */
    boolean isVisible();

    /**
     * Sets the visible.
     *
     * @param visible the new visible
     */
    void setVisible(boolean visible);

    /**
     * Update.
     *
     * @param delta the delta
     */
    void update(float delta);

    /**
     * Render.
     *
     * @param parentG the parent g
     * @param delta the delta
     */
    void render(IGraphics parentG, float delta);

    /**
     * Sets the delta processor.
     *
     * @param processor the new delta processor
     */
    void setDeltaProcessor(IDeltaProcessor processor);

    /**
     * On key event.
     *
     * @param keyEvent the key event
     * @return true, if successful
     */
    boolean onKeyEvent(IGameEvent keyEvent);

    /**
     * On touch event.
     *
     * @param touchEvent the touch event
     * @return true, if successful
     */
    boolean onTouchEvent(IGameEvent touchEvent);

    /**
     * Checks if is action.
     *
     * @return true, if is action
     */
    boolean isAction();

    /**
     * Gets the attributes.
     *
     * @return the attributes
     */
    DeltaAttributes getAttributes();

    /**
     * Sets the attributes.
     *
     * @param attributes the new attributes
     */
    void setAttributes(DeltaAttributes attributes);

    /**
     * Sets the bounds.
     *
     * @param x the x
     * @param y the y
     * @param w the w
     * @param h the h
     */
    void setBounds(int x, int y, int w, int h);

    /**
     * Sets the bounds.
     *
     * @param x the x
     * @param y the y
     * @param w the w
     * @param h the h
     * @param srcX the src x
     * @param srcY the src y
     */
    void setBounds(int x, int y, int w, int h, int srcX, int srcY);

    /**
     * Dispose.
     */
    void dispose();

    /**
     * The Class Bounds.
     */
    public class Bounds
    {
        
        /** The src x. */
        public int srcX;
        
        /** The src y. */
        public int srcY;
        
        /** The x. */
        public int x;
        
        /** The y. */
        public int y;
        
        /** The width. */
        public int width;
        
        /** The height. */
        public int height;
        
        /** The scale. */
        public float scale = 1.0f;

        /**
         * Instantiates a new bounds.
         *
         * @param x the x
         * @param y the y
         * @param width the width
         * @param height the height
         */
        public Bounds(final int x, final int y, final int width, final int height)
        {
            this(x, y, width, height, 0, 0);
        }

        /**
         * Instantiates a new bounds.
         *
         * @param x the x
         * @param y the y
         * @param width the width
         * @param height the height
         * @param srcX the src x
         * @param srcY the src y
         */
        public Bounds(final int x, final int y, final int width, final int height, final int srcX, final int srcY)
        {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
            this.srcX = srcX;
            this.srcY = srcY;
        }

        /**{@inheritDoc}*/
        @Override
        public String toString()
        {
            return "x:" + x + ", y:" + y + ", w:" + width + ", h:" + height + ", srcX:" + srcX + ", srcY:" + srcY
                    + ", X:" + scale;
        }
    }
}
