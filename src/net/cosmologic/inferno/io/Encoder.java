/*
 * 
 */
package net.cosmologic.inferno.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.spec.SecretKeySpec;

/**
 * The Class Encoder.
 * 
 * @author Evren Ozcan
 */
public final class Encoder
{

    /** The key bytes. */
    private static byte[] keyBytes = { (byte) 0x01, (byte) 0xE3, (byte) 0xA2, (byte) 0x19, (byte) 0x59, (byte) 0xBD,
            (byte) 0xEE, (byte) 0xAB };

    /** The Constant keySpec. */
    private static final SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "DES");

    /**
     * Gets the input stream.
     *
     * @param in the in
     * @return the input stream
     * @throws Exception the exception
     */
    public static BufferedInputStream getInputStream(final InputStream in) throws Exception
    {
        final Cipher ecipher = Cipher.getInstance("DES");
        ecipher.init(Cipher.DECRYPT_MODE, keySpec);
        return new BufferedInputStream(new CipherInputStream(in, ecipher), 128);
    }

    /**
     * Gets the output stream.
     *
     * @param out the out
     * @return the output stream
     * @throws Exception the exception
     */
    public static BufferedOutputStream getOutputStream(final OutputStream out) throws Exception
    {
        final Cipher ecipher = Cipher.getInstance("DES");
        ecipher.init(Cipher.ENCRYPT_MODE, keySpec);
        return new BufferedOutputStream(new CipherOutputStream(out, ecipher), 128);
    }

    /**
     * Encrypt.
     *
     * @param in the in
     * @param out the out
     * @throws Exception the exception
     */
    public static void encrypt(final File in, final File out) throws Exception
    {
        final FileInputStream is = new FileInputStream(in);
        final Cipher ecipher = Cipher.getInstance("DES");
        ecipher.init(Cipher.ENCRYPT_MODE, keySpec);
        final CipherOutputStream os = new CipherOutputStream(new FileOutputStream(out), ecipher);
        copy(is, os);
        os.close();
    }

    /**
     * Decrypt.
     *
     * @param in the in
     * @param out the out
     * @throws Exception the exception
     */
    public static void decrypt(final File in, final File out) throws Exception
    {
        final Cipher ecipher = Cipher.getInstance("DES");
        ecipher.init(Cipher.DECRYPT_MODE, keySpec);
        final CipherInputStream is = new CipherInputStream(new FileInputStream(in), ecipher);
        final FileOutputStream os = new FileOutputStream(out);
        copy(is, os);
        is.close();
        os.close();
    }

    /**
     * Copies a stream.
     *
     * @param is the is
     * @param os the os
     * @throws IOException Signals that an I/O exception has occurred.
     */
    private static void copy(final InputStream is, final OutputStream os) throws IOException
    {
        int i;
        final byte[] b = new byte[1024];
        while ((i = is.read(b)) != -1)
        {
            os.write(b, 0, i);
        }
    }

    /**
     * Instantiates a new encoder.
     */
    private Encoder()
    {
        assert false : "Fully static class";
    }

}
