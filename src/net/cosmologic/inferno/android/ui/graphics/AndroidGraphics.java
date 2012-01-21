/*
 * 
 */
package net.cosmologic.inferno.android.ui.graphics;

import net.cosmologic.inferno.IContext;
import net.cosmologic.inferno.animation.InfernoAnimation;
import net.cosmologic.inferno.animation.InfernoFrame;
import net.cosmologic.inferno.ui.graphics.AbstractGraphics;
import net.cosmologic.inferno.ui.graphics.IBitmapWrapper;
import net.cosmologic.inferno.ui.graphics.IBitmapWrapper.BitmapFormat;
import net.cosmologic.inferno.ui.screen.IBitmapDrawable;
import net.cosmologic.inferno.ui.screen.ICheckBoxDrawable;
import net.cosmologic.inferno.ui.screen.IComponent;
import net.cosmologic.inferno.ui.screen.IComponent.Bounds;
import net.cosmologic.inferno.ui.screen.ITextDrawable;
import net.cosmologic.inferno.util.StringUtils;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.RectF;

/**
 * The Class AndroidGraphics.
 * 
 * @author Evren Ozcan
 */
public class AndroidGraphics extends AbstractGraphics
{

    /** The Constant TAG. */
    public static final String TAG = "InfernoAndroidGraphics";
    
    /** The Constant DEFAULT_PAINT. */
    public static final Paint DEFAULT_PAINT = new Paint();

    /** The visual area. */
    protected Canvas visualArea;
    
    /** The src area. */
    protected Rect srcArea = new Rect();
    
    /** The target area. */
    protected Rect targetArea = new Rect();

    /**
     * Instantiates a new android graphics.
     *
     * @param context the context
     * @param id the id
     * @param width the width
     * @param height the height
     * @param format the format
     */
    public AndroidGraphics(final IContext context, final String id, final int width, final int height,
            final BitmapFormat format)
    {
        super(context, id, width, height, format);
        visualArea = new Canvas(((AndroidBitmapWrapper) getFrameBuffer()).getRaw());
    }

    /**{@inheritDoc}*/
    @Override
    public void clearTransparent()
    {
        visualArea.drawColor(0, PorterDuff.Mode.CLEAR);
    }

    /**{@inheritDoc}*/
    @Override
    public void clear(final int color)
    {
        visualArea.drawRGB((color & 0xff0000) >> 16, (color & 0xff00) >> 8, (color & 0xff));
    }

    /**{@inheritDoc}*/
    @Override
    public void clear()
    {
        clear(0);
    }

    /**{@inheritDoc}*/
    @Override
    public void drawPixel(final int x, final int y, final int color)
    {
        DEFAULT_PAINT.setColor(color);
        visualArea.drawPoint(x, y, DEFAULT_PAINT);
    }

    /**{@inheritDoc}*/
    @Override
    public void drawLine(final int x, final int y, final int x2, final int y2, final int color)
    {
        DEFAULT_PAINT.setColor(color);
        visualArea.drawLine(x, y, x2, y2, DEFAULT_PAINT);
    }

    /**{@inheritDoc}*/
    @Override
    public void drawRect(final int x, final int y, final int width, final int height, final int color)
    {
        final float strokePrepaint = DEFAULT_PAINT.getStrokeWidth();
        DEFAULT_PAINT.setStyle(Style.STROKE);
        DEFAULT_PAINT.setStrokeWidth(0.5f);
        fillRect(x, y, width, height, color);
        DEFAULT_PAINT.setStyle(Style.FILL);
        DEFAULT_PAINT.setStrokeWidth(strokePrepaint);
    }

    /**{@inheritDoc}*/
    @Override
    public void drawRect(final int x, final int y, final int width, final int height, final int color, final int alpha)
    {
        final int alphaPrePaint = DEFAULT_PAINT.getAlpha();
        DEFAULT_PAINT.setAlpha(alpha);
        drawRect(x, y, width, height, color);
        DEFAULT_PAINT.setAlpha(alphaPrePaint);
    }

    /**{@inheritDoc}*/
    @Override
    public void fillRect(final int x, final int y, final int width, final int height, final int color)
    {
        final int colorPrePaint = DEFAULT_PAINT.getColor();
        DEFAULT_PAINT.setColor(color);
        visualArea.drawRect(x, y, x + width - 1, y + height - 1, DEFAULT_PAINT);
        DEFAULT_PAINT.setColor(colorPrePaint);
    }

    /**{@inheritDoc}*/
    @Override
    public void fillRect(final int x, final int y, final int width, final int height, final int color, final int alpha)
    {
        final int alphaPrePaint = DEFAULT_PAINT.getAlpha();
        final int colorPrePaint = DEFAULT_PAINT.getColor();
        DEFAULT_PAINT.setColor(color);
        DEFAULT_PAINT.setAlpha(alpha);
        visualArea.drawRect(x, y, x + width - 1, y + height - 1, DEFAULT_PAINT);
        DEFAULT_PAINT.setAlpha(alphaPrePaint);
        DEFAULT_PAINT.setColor(colorPrePaint);
    }

    /**{@inheritDoc}*/
    @Override
    public void drawRoundRect(final IComponent component, final int x, final int y, final int color, final int alpha)
    {
        final float before = DEFAULT_PAINT.getStrokeWidth();
        DEFAULT_PAINT.setStyle(Style.STROKE);
        DEFAULT_PAINT.setStrokeWidth(0.5f);
        fillRoundRect(component, x, y, color, alpha);
        DEFAULT_PAINT.setStyle(Style.FILL);
        DEFAULT_PAINT.setStrokeWidth(before);
    }

    /**{@inheritDoc}*/
    @Override
    public void fillRoundRect(final IComponent component, final int x, final int y, final int color, final int alpha)
    {
        final int defcolor = DEFAULT_PAINT.getColor();
        final int defaultAlpha = DEFAULT_PAINT.getAlpha();
        DEFAULT_PAINT.setColor(color);
        DEFAULT_PAINT.setAlpha(alpha);
        visualArea.drawRoundRect(new RectF(new Rect(0, 0, component.getBounds().width, component.getBounds().height)),
                8f, 8f, DEFAULT_PAINT);
        DEFAULT_PAINT.setAlpha(defaultAlpha);
        DEFAULT_PAINT.setColor(defcolor);
    }

    /**{@inheritDoc}*/
    @Override
    public void drawBitmap(final IBitmapWrapper wrapper, final int x, final int y)
    {
        visualArea.drawBitmap((Bitmap) wrapper.getRaw(), x, y, null);
    }

    /**{@inheritDoc}*/
    @Override
    public void drawText(final String text, final int x, final int y, final int color)
    {
        final int defcolor = DEFAULT_PAINT.getColor();
        DEFAULT_PAINT.setColor(color);
        visualArea.drawText(text, x, y, DEFAULT_PAINT);
        DEFAULT_PAINT.setColor(defcolor);
    }

    /**{@inheritDoc}*/
    @Override
    public void drawText(final String text, final int x, final int y, final int color, final int size)
    {
        final int defcolor = DEFAULT_PAINT.getColor();
        final float defaultSize = DEFAULT_PAINT.getTextSize();
        DEFAULT_PAINT.setColor(color);
        DEFAULT_PAINT.setTextSize(size);
        visualArea.drawText(text, x, y, DEFAULT_PAINT);
        DEFAULT_PAINT.setColor(defcolor);
        DEFAULT_PAINT.setTextSize(defaultSize);
    }

    /**
     * Draw text.
     *
     * @param text the text
     * @param x the x
     * @param y the y
     * @param paint the paint
     */
    public void drawText(final String text, final int x, final int y, final Paint paint)
    {
        visualArea.drawText(text, x, y, paint);
    }

    /**{@inheritDoc}*/
    @Override
    public void drawCircle(final int cx, final int cy, final int radius, final int color)
    {
        DEFAULT_PAINT.setColor(color);
        DEFAULT_PAINT.setStyle(Style.FILL);
        visualArea.drawCircle(cx, cy, radius, DEFAULT_PAINT);
    }

    /**{@inheritDoc}*/
    @Override
    public int getTextWidth(final String text, final float size)
    {
        if (StringUtils.isBlank(text))
        {
            return 0;
        }
        final float defaultSize = DEFAULT_PAINT.getTextSize();
        final float[] w = new float[text.length()];
        DEFAULT_PAINT.setTextSize(size);
        DEFAULT_PAINT.getTextWidths(text, w);
        DEFAULT_PAINT.setTextSize(defaultSize);
        int tw = 0;
        for (final float ws : w)
        {
            tw += ws;
        }
        return tw;
    }

    /**{@inheritDoc}*/
    @Override
    public void drawComponent(final IComponent component)
    {
        if (component instanceof ITextDrawable)
        {
            drawComponent((ITextDrawable) component);
        }
        else if (component instanceof IBitmapDrawable)
        {
            if (null != component.getAttributes())
            {
                final int defaultAlpha = DEFAULT_PAINT.getAlpha();
                DEFAULT_PAINT.setAlpha(component.getAttributes().alpha);
                drawComponent((IBitmapDrawable) component);
                DEFAULT_PAINT.setAlpha(defaultAlpha);
            }
            else
            {
                drawComponent((IBitmapDrawable) component);
            }

        }
    }

    /**{@inheritDoc}*/
    @Override
    public void drawComponent(final IComponent component, final int alpha)
    {
        final int defaultAlpha = DEFAULT_PAINT.getAlpha();
        DEFAULT_PAINT.setAlpha(alpha);
        drawComponent(component);
        DEFAULT_PAINT.setAlpha(defaultAlpha);
    }

    /**{@inheritDoc}*/
    @Override
    public void drawAnitmation(final InfernoAnimation animation)
    {
        final InfernoFrame frame = animation.getCurrentFrame();
        if (null == frame)
        {
            return;
        }
        final Bitmap bitmap = (Bitmap) frame.getBitmap().getRaw();
        srcArea.left = frame.srcX;
        srcArea.top = frame.srcY;
        srcArea.right = frame.srcX + frame.width;
        srcArea.bottom = frame.srcY + frame.height;

        int relativeX = frame.x;
        int relativeY = frame.y;
        int targetWidth = frame.x + frame.width;
        int targetHeight = frame.y + frame.height;
        if (frame.scale < 1)
        {
            targetWidth = (int) (frame.width * frame.scale);
            targetHeight = (int) (frame.height * frame.scale);
            // center styled
            relativeX = frame.x + frame.width / 2 - targetWidth / 2;
            relativeY = frame.y + frame.height / 2 - targetHeight / 2;
            targetWidth += relativeX;
            targetHeight += relativeY;
        }

        targetArea.left = relativeX;
        targetArea.top = relativeY;
        targetArea.right = targetWidth;
        targetArea.bottom = targetHeight;
        visualArea.drawBitmap(bitmap, srcArea, targetArea, DEFAULT_PAINT);

    }

    /**
     * Draw component.
     *
     * @param drawable the drawable
     */
    public void drawComponent(final IBitmapDrawable drawable)
    {
        // Logger.debug(TAG, getId() + " drawing " + drawable.toString());
        final Bitmap bitmap = (Bitmap) drawable.getBitmap().getRaw();
        final Bounds bounds = drawable.getBounds();
        if ((bounds.scale == 1) && (bitmap.getHeight() == bounds.height) && (bitmap.getWidth() == bounds.width))
        {
            visualArea.drawBitmap(bitmap, bounds.x, bounds.y, DEFAULT_PAINT);
        }
        else
        {
            srcArea.left = bounds.srcX;
            srcArea.top = bounds.srcY;
            srcArea.right = bounds.srcX + bounds.width;
            srcArea.bottom = bounds.srcY + bounds.height;

            int relativeX = bounds.x;
            int relativeY = bounds.y;
            int targetWidth = bounds.x + bounds.width;
            int targetHeight = bounds.y + bounds.height;
            if (bounds.scale < 1)
            {
                targetWidth = (int) (bounds.width * bounds.scale);
                targetHeight = (int) (bounds.height * bounds.scale);
                // center styled
                relativeX = bounds.x + bounds.width / 2 - targetWidth / 2;
                relativeY = bounds.y + bounds.height / 2 - targetHeight / 2;
                targetWidth += relativeX;
                targetHeight += relativeY;
            }

            targetArea.left = relativeX;
            targetArea.top = relativeY;
            targetArea.right = targetWidth;
            targetArea.bottom = targetHeight;
            visualArea.drawBitmap(bitmap, srcArea, targetArea, DEFAULT_PAINT);
        }
    }

    /**
     * Draw component works.
     *
     * @param drawable the drawable
     */
    public void drawComponentWorks(final IBitmapDrawable drawable)
    {
        final Bounds bounds = drawable.getBounds();
        srcArea.left = bounds.srcX;
        srcArea.top = bounds.srcY;
        srcArea.right = bounds.srcX + bounds.width;
        srcArea.bottom = bounds.srcY + bounds.height;
        final int width = (int) (bounds.width * bounds.scale);
        final int height = (int) (bounds.height * bounds.scale);
        if (null != drawable.getParent())
        {
            final Bounds parent = drawable.getParent().getBounds();
            final int rectX = bounds.x + (parent.width - width) / 2;
            final int rectY = bounds.y + (parent.height - height) / 2;
            targetArea.left = rectX;
            targetArea.top = rectY;
        }
        else
        {
            final int rectX = bounds.x + (bounds.width - width) / 2;
            final int rectY = bounds.y + (bounds.height - height) / 2;
            targetArea.left = rectX;
            targetArea.top = rectY;
        }
        targetArea.right = (int) ((targetArea.left + bounds.width) * bounds.scale);
        targetArea.bottom = (int) ((targetArea.top + bounds.height) * bounds.scale);
        visualArea.drawBitmap((Bitmap) drawable.getBitmap().getRaw(), srcArea, targetArea, DEFAULT_PAINT);
    }

    /**
     * Draw component.
     *
     * @param text the text
     */
    public void drawComponent(final ITextDrawable text)
    {
        final int color = DEFAULT_PAINT.getColor();
        final float size = DEFAULT_PAINT.getTextSize();
        final Bounds bounds = text.getBounds();
        final IBitmapWrapper bitmap = text.getBitmap();
        int leftPlus = text.getMargins().left;
        if (null != bitmap)
        {
            drawBitmap(bitmap, bounds.x + text.getMargins().spacing, bounds.y + text.getMargins().spacing);
            leftPlus = text.getMargins().spacing + bitmap.getWidth();
        }
        if (StringUtils.isNotBlank(text.getText()))
        {
            DEFAULT_PAINT.setColor(text.getTextColor());
            DEFAULT_PAINT.setTextSize(text.getTextSize());
            drawText(text.getText(), bounds.x + leftPlus, bounds.y + 20 + text.getMargins().top, DEFAULT_PAINT);
        }
        if (text instanceof ICheckBoxDrawable)
        {
            final ICheckBoxDrawable checkBox = (ICheckBoxDrawable) text;
            final IBitmapWrapper icon = checkBox.getRolloverIcon();
            // draw the checkbox checked/unchecked icons
            final int left = bounds.x + bounds.width - (icon.getWidth() + text.getMargins().spacing);
            drawBitmap(icon, left, bounds.y + (bounds.height - icon.getHeight()) / 2);
        }
        DEFAULT_PAINT.setColor(color);
        DEFAULT_PAINT.setTextSize(size);
    }
}
