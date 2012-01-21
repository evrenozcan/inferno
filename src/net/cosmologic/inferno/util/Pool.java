/*
 * 
 */
package net.cosmologic.inferno.util;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * The Class Pool.
 *
 * @param <T> the generic type
 * 
 * @author Evren Ozcan
 */
public class Pool<T>
{

    /**
     * A factory for creating PoolObject objects.
     *
     * @param <T> the generic type
     */
    public interface PoolObjectFactory<T>
    {
        
        /**
         * Creates a new PoolObject object.
         *
         * @return the T
         */
        public T createObject();
    }

    /** The objects. */
    private final Queue<T> objects;
    
    /** The factory. */
    private final PoolObjectFactory<T> factory;
    
    /** The pool size. */
    private final int poolSize;

    /**
     * Instantiates a new pool.
     *
     * @param poolSize the pool size
     */
    public Pool(final int poolSize)
    {
        this(null, poolSize);
    }

    /**
     * Instantiates a new pool.
     *
     * @param factory the factory
     * @param poolSize the pool size
     */
    public Pool(final PoolObjectFactory<T> factory, final int poolSize)
    {
        this.factory = factory;
        this.poolSize = poolSize;
        this.objects = new ConcurrentLinkedQueue<T>();
    }

    /**
     * Peek.
     *
     * @return the t
     */
    public T peek()
    {
        return objects.peek();
    }

    /**
     * Checks for pending.
     *
     * @return true, if successful
     */
    public boolean hasPending()
    {
        return null != objects.peek();
    }

    /**
     * Poll.
     *
     * @return the t
     */
    public T poll()
    {
        if (objects.isEmpty())
        {
            if (null != factory)
            {
                return factory.createObject();
            }
            else
            {
                return null;
            }
        }
        else
        {
            return objects.poll();
        }
    }

    /**
     * Gets the size.
     *
     * @return the size
     */
    public int getSize()
    {
        return poolSize;
    }

    /**
     * Gets the objects.
     *
     * @return the objects
     */
    public Queue<T> getObjects()
    {
        return objects;
    }

    /**
     * Removes the.
     *
     * @param object the object
     */
    public void remove(final T object)
    {
        objects.remove(object);
    }

    /**
     * Offer quietely.
     *
     * @param object the object
     * @param forced the forced
     * @return true, if successful
     */
    public boolean offerQuietely(final T object, final boolean forced)
    {
        if (forced)
        {
            objects.offer(object);
            return true;
        }
        else
        {
            return offerQuietely(object);
        }
    }

    /**
     * Offer quietely.
     *
     * @param object the object
     * @return true, if successful
     */
    public boolean offerQuietely(final T object)
    {
        if (objects.size() < poolSize)
        {
            objects.offer(object);
            return true;
        }
        else
        {
            return false;
        }
    }
}
