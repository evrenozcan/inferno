/*
 * 
 */
package net.cosmologic.inferno.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.Set;

import net.cosmologic.inferno.IContext;
import net.cosmologic.inferno.event.IEventListener;

/**
 * The Class IOUtils.
 * 
 * @author Evren Ozcan
 */
public final class IOUtils
{

    /** The Constant NEW_LINE. */
    private static final byte[] NEW_LINE = System.getProperty("line.separator").getBytes();

    /**
     * Instantiates a new iO utils.
     */
    private IOUtils()
    {
        assert false : "Fully static class";
    }

    /**
     * Close quietely.
     *
     * @param is the is
     */
    public static void closeQuietely(final InputStream is)
    {
        if (null == is)
        {
            return;
        }
        try
        {
            is.close();
        }
        catch (final Exception e)
        {
            ;
        }
    }

    /**
     * Close quietely.
     *
     * @param os the os
     */
    public static void closeQuietely(final OutputStream os)
    {
        if (null == os)
        {
            return;
        }
        try
        {
            os.flush();
            os.close();
        }
        catch (final Exception e)
        {
            ;
        }
    }

    /**
     * Close quietely.
     *
     * @param is the is
     */
    public static void closeQuietely(final Reader is)
    {
        if (null == is)
        {
            return;
        }
        try
        {
            is.close();
        }
        catch (final Exception e)
        {
            ;
        }
    }

    /**
     * Close quietely.
     *
     * @param os the os
     */
    public static void closeQuietely(final Writer os)
    {
        if (null == os)
        {
            return;
        }
        try
        {
            os.flush();
            os.close();
        }
        catch (final Exception e)
        {
            ;
        }
    }

    /**
     * Write line.
     *
     * @param stream the stream
     * @param string the string
     */
    public static void writeLine(final OutputStream stream, final String string)
    {
        try
        {
            stream.write(string.getBytes());
            stream.write(NEW_LINE);
        }
        catch (final Exception e)
        {
            ;
        }
    }

    /**
     * Save context to file.
     *
     * @param context the context
     * @param fileName the file name
     * @param listener the listener
     */
    public static void saveContextToFile(final IContext context, final String fileName,
            final IEventListener<Boolean> listener)
    {
        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                BufferedWriter writer = null;
                try
                {
                    writer = new BufferedWriter(new OutputStreamWriter(context.getFileIO().getOutputStream(fileName)));
                    final Set<String> keys = context.getKeys();
                    for (final String key : keys)
                    {
                        if (key.startsWith(IContext.ATTR_CONFIG_PREFIX))
                        {
                            final Object value = context.getAttribute(key);
                            try
                            {
                                writer.write(key + " = " + String.valueOf(value) + "\r\n");
                                writer.flush();
                            }
                            catch (final IOException e)
                            {
                            }
                        }
                    }
                }
                catch (final Exception e)
                {

                }
                finally
                {
                    if (null != listener)
                    {
                        listener.onEnd();
                    }
                    closeQuietely(writer);
                }
            }
        }, "save-content").start();
    }

    /**
     * Load context from file.
     *
     * @param context the context
     * @param fileName the file name
     */
    public static void loadContextFromFile(final IContext context, final String fileName)
    {
        BufferedReader reader = null;
        try
        {
            reader = new BufferedReader(new InputStreamReader(context.getFileIO().getFileAsStream(fileName)));
            String line = reader.readLine();
            while (null != line)
            {
                final String[] pair = StringUtils.getStringAsPair(line);
                context.setAttribute(pair[0], pair[1]);
                line = reader.readLine();
            }

        }
        catch (final IOException e)
        {
            ;
        }
        finally
        {
            closeQuietely(reader);
        }
    }
}
