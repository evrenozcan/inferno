/*
 * 
 */
package net.cosmologic.inferno.android;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import net.cosmologic.inferno.IContext;
import net.cosmologic.inferno.ResourceRepository;
import net.cosmologic.inferno.android.io.AndroidFileIO;
import net.cosmologic.inferno.android.io.AndroidGameInput;
import net.cosmologic.inferno.android.reactor.AndroidTribune;
import net.cosmologic.inferno.android.sound.AndroidAudio;
import net.cosmologic.inferno.util.NumberUtils;

/**
 * The Class InfernoAndroidContext.
 * 
 * @author Evren Ozcan
 */
public class InfernoAndroidContext implements IContext
{

    /** The Constant INPUT_QUEUE_SIZE. */
    public static final int INPUT_QUEUE_SIZE = 18;
    
    /** The Constant SOUND_QUEUE_SIZE. */
    public static final int SOUND_QUEUE_SIZE = 8;
    
    /** The Constant PROCESS_QUEUE_SIZE. */
    public static final int PROCESS_QUEUE_SIZE = 10;
    
    /** The Constant ATTR_INTERNAL_PREFIX. */
    private static final String ATTR_INTERNAL_PREFIX = "inferno.";
    
    /** The attributes. */
    private Map<String, Object> attributes;
    
    /** The activity. */
    private final InfernoAndroidActivity2D activity;
    
    /** The tribune. */
    private AndroidTribune tribune;
    
    /** The audio. */
    private AndroidAudio audio;
    
    /** The input. */
    private AndroidGameInput input;
    
    /** The file io. */
    private AndroidFileIO fileIO;
    
    /** The platform. */
    private AndroidGamePlatform platform;
    
    /** The updated. */
    private boolean updated = false;
    
    /** The repository. */
    private final ResourceRepository repository;

    /**
     * Instantiates a new inferno android context.
     *
     * @param activity the activity
     */
    public InfernoAndroidContext(final InfernoAndroidActivity2D activity)
    {
        this.activity = activity;
        repository = new ResourceRepository();
        fileIO = new AndroidFileIO(this, "cosmologic/inferno");
        setAttribute(Constants.ATTRIBUTE_INPUT_QUEUE_SIZE, INPUT_QUEUE_SIZE);
        setAttribute(Constants.ATTRIBUTE_SOUND_QUEUE_SIZE, SOUND_QUEUE_SIZE);
        setAttribute(Constants.ATTRIBUTE_ROCESS_QUEUE_SIZE, PROCESS_QUEUE_SIZE);
    }

    /**
     * Initialise.
     */
    private void initialise()
    {
        audio = new AndroidAudio(this);
        input = new AndroidGameInput(this, activity.getScaleX(), activity.getScaleY());
        tribune = new AndroidTribune(this);
    }

    /**
     * Gets the attribute.
     *
     * @param object the object
     * @param key the key
     * @return the attribute
     */
    public Object getAttribute(final Object object, final String key)
    {
        return getAttribute(getStepSpecificKey(object, key));
    }

    /**{@inheritDoc}*/
    @Override
    public ResourceRepository getRepository()
    {
        return repository;
    }

    /**{@inheritDoc}*/
    @Override
    public Object getAttribute(final String key)
    {
        if (null == attributes)
        {
            return null;
        }

        return attributes.get(key);
    }

    /**
     * Gets the step specific key.
     *
     * @param object the object
     * @param key the key
     * @return the step specific key
     */
    private String getStepSpecificKey(final Object object, final String key)
    {
        return String.format("%s.%d.%d.%s", object.getClass().getName(), object.hashCode(),
                System.identityHashCode(object), key);
    }

    /**
     * Removes the attribute.
     *
     * @param key the key
     * @return the object
     */
    public Object removeAttribute(final String key)
    {
        if (null == attributes)
        {
            return null;
        }
        if ((null != key) && key.startsWith(ATTR_INTERNAL_PREFIX))
        {
            return attributes.get(key);
        }

        updated = true;
        return attributes.remove(key);
    }

    /**
     * Sets the attribute.
     *
     * @param object the object
     * @param key the key
     * @param value the value
     */
    public void setAttribute(final Object object, final String key, final Object value)
    {
        setAttribute(getStepSpecificKey(object, key), value);
    }

    /**
     * Sets the attribute.
     *
     * @param key the key
     * @param value the value
     * @param quietely the quietely
     */
    public void setAttribute(final String key, final Object value, final boolean quietely)
    {
        setAttribute(key, value);
        updated = !quietely;
    }

    /**{@inheritDoc}*/
    @Override
    public void setAttribute(final String key, final Object value)
    {
        if (null == attributes)
        {
            // lazy
            attributes = new HashMap<String, Object>();
        }
        if ((null != key) && key.startsWith(ATTR_INTERNAL_PREFIX))
        {
            if ((null != attributes.get(key)) && (null == value))
            {
                return;
            }
        }
        attributes.put(key, value);
        updated = true;
    }

    /**{@inheritDoc}*/
    @Override
    public boolean getAttributeAsBoolean(final String key)
    {
        final Object value = getAttribute(key);
        if (null == value)
        {
            return false;
        }
        if (value instanceof Boolean)
        {
            return (Boolean) value;
        }
        else
        {
            return Boolean.valueOf(String.valueOf(value));
        }
    }

    /**{@inheritDoc}*/
    @Override
    public int getAttributeAsInt(final String key, final int defaultValue)
    {
        final Object value = getAttribute(key);
        if (null == value)
        {
            return defaultValue;
        }

        if (value instanceof Number)
        {
            return ((Number) value).intValue();
        }
        else if (value instanceof String)
        {
            return NumberUtils.getInt((String) value, defaultValue);
        }

        return defaultValue;

    }

    /**
     * Gets the activity.
     *
     * @return the activity
     */
    public InfernoAndroidActivity2D getActivity()
    {
        return activity;
    }

    /**
     * Gets the platform.
     *
     * @return the platform
     */
    public AndroidGamePlatform getPlatform()
    {
        return platform;
    }

    /**
     * Sets the platform.
     *
     * @param platform the new platform
     */
    public void setPlatform(final AndroidGamePlatform platform)
    {
        this.platform = platform;
        initialise();
    }

    /**
     * Sets the tribune.
     *
     * @param tribune the new tribune
     */
    public void setTribune(final AndroidTribune tribune)
    {
        this.tribune = tribune;
    }

    /**
     * Sets the audio.
     *
     * @param audio the new audio
     */
    public void setAudio(final AndroidAudio audio)
    {
        this.audio = audio;
    }

    /**
     * Sets the input.
     *
     * @param input the new input
     */
    public void setInput(final AndroidGameInput input)
    {
        this.input = input;
    }

    /**
     * Sets the file io.
     *
     * @param fileIO the new file io
     */
    public void setFileIO(final AndroidFileIO fileIO)
    {
        this.fileIO = fileIO;
    }

    /**{@inheritDoc}*/
    @Override
    public void dispose()
    {
        attributes.clear();
    }

    /**{@inheritDoc}*/
    @Override
    public AndroidGameInput getGameInput()
    {
        return input;
    }

    /**{@inheritDoc}*/
    @Override
    public AndroidFileIO getFileIO()
    {
        return fileIO;
    }

    /**{@inheritDoc}*/
    @Override
    public AndroidAudio getAudio()
    {
        return audio;
    }

    /**{@inheritDoc}*/
    @Override
    public AndroidTribune getTribune()
    {
        return tribune;
    }

    /**{@inheritDoc}*/
    @Override
    public Set<String> getKeys()
    {
        if (null == attributes)
        {
            return new TreeSet<String>();
        }
        return attributes.keySet();
    }

    /**{@inheritDoc}*/
    @Override
    public AndroidGamePlatform getGamePlatform()
    {
        return platform;
    }

    /**{@inheritDoc}*/
    @Override
    public boolean clearConfiguration(final String fileName)
    {
        return getFileIO().getExternalFile(fileName).delete();
    }

    /**{@inheritDoc}*/
    @Override
    public void resetConfiguration()
    {
        final List<String> configAttributes = new ArrayList<String>();
        configAttributes.addAll(getKeys());
        for (final String key : configAttributes)
        {
            if (key.startsWith(ATTR_CONFIG_PREFIX))
            {
                attributes.remove(key);
            }
        }
        updated = false;
    }

    /**{@inheritDoc}*/
    @Override
    public boolean isUpdated()
    {
        return updated;
    }

    /**{@inheritDoc}*/
    @Override
    public String getStringResource(final int id)
    {
        return activity.getString(id);
    }

    /**{@inheritDoc}*/
    @Override
    public int getColorResource(final int id)
    {
        return activity.getResources().getColor(id);
    }
}
