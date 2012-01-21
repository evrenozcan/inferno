/*
 * 
 */
package net.cosmologic.inferno;

import java.util.HashMap;
import java.util.Map;

import net.cosmologic.inferno.sound.ISoundEffect;
import net.cosmologic.inferno.sound.ITrack;
import net.cosmologic.inferno.ui.graphics.IBitmapWrapper;

/**
 * The Class ResourceRepository.
 * 
 * @author Evren Ozcan
 */
public final class ResourceRepository
{

    /** The repository. */
    private final Map<String, IResource> repository;

    /**
     * Instantiates a new resource repository.
     */
    public ResourceRepository()
    {
        repository = new HashMap<String, IResource>();
    }

    /**
     * Adds the resource.
     * 
     * @param resource
     *            the resource
     */
    public void addResource(final IResource resource)
    {
        repository.put(resource.getId(), resource);
    }

    /**
     * Gets the resource.
     * 
     * @param id
     *            the id
     * @return the resource
     */
    public IResource getResource(final String id)
    {
        return repository.get(id);
    }

    /**
     * Gets the bitmap.
     * 
     * @param id
     *            the id
     * @return the bitmap
     */
    public IBitmapWrapper getBitmap(final String id)
    {
        return (IBitmapWrapper) getResource(id);
    }

    /**
     * Gets the track.
     * 
     * @param id
     *            the id
     * @return the track
     */
    public ITrack getTrack(final String id)
    {
        return (ITrack) getResource(id);
    }

    /**
     * Gets the effect.
     * 
     * @param id
     *            the id
     * @return the effect
     */
    public ISoundEffect getEffect(final String id)
    {
        return (ISoundEffect) getResource(id);
    }

    /**
     * Removes the resource.
     * 
     * @param resource
     *            the resource
     */
    public void removeResource(final IResource resource)
    {
        removeResource(resource.getId());
    }

    /**
     * Removes the resource.
     * 
     * @param id
     *            the id
     */
    public void removeResource(final String id)
    {
        repository.remove(id);
    }

    /**
     * Dispose.
     */
    public void dispose()
    {
        repository.clear();
    }
}
