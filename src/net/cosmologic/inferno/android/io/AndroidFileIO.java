/*
 * 
 */
package net.cosmologic.inferno.android.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import net.cosmologic.inferno.InfernoException;
import net.cosmologic.inferno.android.InfernoAndroidContext;
import net.cosmologic.inferno.android.ui.graphics.AndroidBitmapWrapper;
import net.cosmologic.inferno.android.util.InfernoLogger;
import net.cosmologic.inferno.io.Encoder;
import net.cosmologic.inferno.io.IFileIO;
import net.cosmologic.inferno.ui.graphics.IBitmapWrapper.BitmapFormat;
import net.cosmologic.inferno.util.IOUtils;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.os.Environment;

/**
 * The Class AndroidFileIO.
 * 
 * @author Evren Ozcan
 */
public class AndroidFileIO implements IFileIO
{

    /** The assets. */
    private final AssetManager assets;
    
    /** The external path. */
    private final String externalPath;
    
    /** The Constant TAG. */
    public static final String TAG = "Inferno-AndroidFileIO";
    
    /** The config folder. */
    private final File configFolder;

    /**
     * Instantiates a new android file io.
     *
     * @param context the context
     * @param filePath the file path
     */
    public AndroidFileIO(final InfernoAndroidContext context, final String filePath)
    {
        this.assets = context.getActivity().getAssets();
        this.externalPath = Environment.getExternalStorageDirectory().getAbsolutePath();
        configFolder = new File(externalPath, filePath);
        if (!configFolder.exists())
        {
            configFolder.mkdirs();
        }
    }

    /**{@inheritDoc}*/
    @Override
    public InputStream getAssetAsStream(final String fileName)
    {
        try
        {
            final InputStream in = assets.open(fileName);
            if (fileName.contains("/raw/"))
            {
                return Encoder.getInputStream(in);
            }
            else
            {
                return assets.open(fileName);
            }
        }
        catch (final Exception e)
        {
            InfernoLogger.error(TAG, e);
            throw new InfernoException(String.format("Can not load asset: %s", fileName, e));
        }
    }

    /**{@inheritDoc}*/
    @Override
    public InputStream getFileAsStream(final String fileName)
    {
        try
        {
            return Encoder.getInputStream(new FileInputStream(new File(configFolder, fileName)));
        }
        catch (final Exception e)
        {
            InfernoLogger.error(TAG, e);
            throw new InfernoException(String.format("Can not open stream fileName: %s", fileName, e));
        }
    }

    /**{@inheritDoc}*/
    @Override
    public OutputStream getOutputStream(final String fileName)
    {
        try
        {
            return Encoder.getOutputStream(new FileOutputStream(new File(configFolder, fileName)));
        }
        catch (final Exception e)
        {
            InfernoLogger.error(TAG, e);
            throw new InfernoException(String.format("Can not load asset: %s", fileName, e));
        }
    }

    /**{@inheritDoc}*/
    @Override
    public AndroidBitmapWrapper createBitmapWrapper(final String id, final int width, final int height,
            final BitmapFormat format)
    {
        final Bitmap bitmap = Bitmap.createBitmap(width, height, getConfig(format));
        return new AndroidBitmapWrapper(id, bitmap, format);
    }

    /**{@inheritDoc}*/
    @Override
    public AndroidBitmapWrapper createBitmapWrapper(final String source, final BitmapFormat format)
    {
        final Options options = new Options();
        options.inPreferredConfig = getConfig(format);
        try
        {
            final Bitmap bitmap = loadBitmap(source);
            return new AndroidBitmapWrapper(source, bitmap, getBitmapFormat(bitmap));
        }
        catch (final Exception e)
        {
            throw new InfernoException(String.format("Can not create pixel from %s", source), e);
        }
    }

    /**
     * Load bitmap.
     *
     * @param fileName the file name
     * @return the bitmap
     * @throws Exception the exception
     */
    private Bitmap loadBitmap(final String fileName) throws Exception
    {
        InputStream is = null;
        try
        {
            is = getAssetAsStream(String.format("drawable/%s", fileName));
            final Bitmap bitmap = BitmapFactory.decodeStream(is);
            if (null == bitmap)
            {
                throw new RuntimeException("Can not decode stream");
            }
            else
            {
                return bitmap;
            }
        }
        finally
        {
            IOUtils.closeQuietely(is);
        }
    }

    /**{@inheritDoc}*/
    @Override
    public File getExternalFile(final String fileName)
    {
        return new File(configFolder, fileName);
    }

    /**
     * Gets the bitmap format.
     *
     * @param bitmap the bitmap
     * @return the bitmap format
     */
    private BitmapFormat getBitmapFormat(final Bitmap bitmap)
    {
        if (bitmap.getConfig() == Config.RGB_565)
        {
            return BitmapFormat.RGB565;
        }
        else if (bitmap.getConfig() == Config.ARGB_4444)
        {
            return BitmapFormat.ARGB4444;
        }
        else
        {
            return BitmapFormat.ARGB8888;
        }
    }

    /**
     * Gets the config.
     *
     * @param format the format
     * @return the config
     */
    private Config getConfig(final BitmapFormat format)
    {
        if (format == BitmapFormat.RGB565)
        {
            return Config.RGB_565;
        }
        else if (format == BitmapFormat.ARGB4444)
        {
            return Config.ARGB_4444;
        }
        else
        {
            return Config.ARGB_8888;
        }
    }
}
