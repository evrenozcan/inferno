/*
 * 
 */
package net.cosmologic.inferno.ui.screen;

/**
 * The Class DeltaAttributes.
 * 
 * @author Evren Ozcan
 */
public class DeltaAttributes
{

    /** The alpha. */
    public int alpha;
    
    /** The angle. */
    public float delay, precision, elapsed, angle;
    
    /** The speed. */
    public int repeatCount, speed;
    
    /** The defaults. */
    private final float[] defaults;
    
    /** The active. */
    public boolean paused, active;
    
    /** The updated. */
    public boolean updated = false;
    
    /** The started. */
    public boolean started = false;
    
    /** The scale. */
    public float scale = 1f;

    /**
     * Instantiates a new delta attributes.
     *
     * @param delay the delay
     * @param precision the precision
     */
    public DeltaAttributes(final float delay, final float precision)
    {
        this(255, 180, delay, precision, 0);
    }

    /**
     * Instantiates a new delta attributes.
     *
     * @param delay the delay
     * @param precision the precision
     * @param speed the speed
     */
    public DeltaAttributes(final float delay, final float precision, final int speed)
    {
        this(255, 180, delay, precision, 0);
        this.speed = speed;
    }

    /**
     * Instantiates a new delta attributes.
     *
     * @param angle the angle
     * @param delay the delay
     * @param precision the precision
     * @param speed the speed
     */
    public DeltaAttributes(final int angle, final float delay, final float precision, final int speed)
    {
        this(255, angle, delay, precision, 0);
        this.speed = speed;
    }

    /**
     * Instantiates a new delta attributes.
     *
     * @param alpha the alpha
     * @param angle the angle
     * @param delay the delay
     * @param precision the precision
     * @param repeatCount the repeat count
     */
    public DeltaAttributes(final int alpha, final int angle, final float delay, final float precision,
            final int repeatCount)
    {
        this.alpha = alpha;
        this.angle = angle;
        this.delay = delay;
        this.precision = precision;
        this.repeatCount = repeatCount;
        defaults = new float[2];
        defaults[0] = delay;
        defaults[1] = precision;
        active = true;
    }

    /**
     * Reset.
     */
    public void reset()
    {
        delay = defaults[0];
        precision = defaults[1];
        paused = false;
        active = true;
        scale = 1f;
        updated = false;
        started = false;
        elapsed = 0;
    }
}