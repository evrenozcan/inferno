/*
 * 
 */
package net.cosmologic.inferno.io;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;

import net.cosmologic.inferno.ui.graphics.IBitmapWrapper;
import net.cosmologic.inferno.ui.graphics.IBitmapWrapper.BitmapFormat;

/**
 * The Interface IFileIO.
 * 
 * @author Evren Ozcan
 */
public interface IFileIO
{
    
    /**
     * Gets the asset as stream.
     *
     * @param fileName the file name
     * @return the asset as stream
     */
    InputStream getAssetAsStream(String fileName);

    /**
     * Gets the file as stream.
     *
     * @param fileName the file name
     * @return the file as stream
     */
    InputStream getFileAsStream(String fileName);

    /**
     * Gets the output stream.
     *
     * @param fileName the file name
     * @return the output stream
     */
    OutputStream getOutputStream(String fileName);

    /**
     * Creates the bitmap wrapper.
     *
     * @param source the source
     * @param format the format
     * @return the i bitmap wrapper
     */
    IBitmapWrapper createBitmapWrapper(String source, final BitmapFormat format);

    /**
     * Creates the bitmap wrapper.
     *
     * @param id the id
     * @param width the width
     * @param height the height
     * @param format the format
     * @return the i bitmap wrapper
     */
    IBitmapWrapper createBitmapWrapper(String id, int width, int height, final BitmapFormat format);

    /**
     * Gets the external file.
     *
     * @param fileName the file name
     * @return the external file
     */
    File getExternalFile(String fileName);
}
