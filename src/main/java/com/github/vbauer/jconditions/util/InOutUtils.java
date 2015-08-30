package com.github.vbauer.jconditions.util;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author Vladislav Bauer
 */

public final class InOutUtils {

    public static final int BUFFER_SIZE = 1024;


    private InOutUtils() {
        throw new UnsupportedOperationException();
    }


    public static void closeQuietly(final Closeable closeable) {
        try {
            closeable.close();
        } catch (final Exception ex) {
            // Ignored
        }
    }

    public static File copyURLContentToFile(
        final URLConnection connection, final String target
    ) throws Exception {
        final byte[] bytes = new byte[BUFFER_SIZE];
        int read;

        InputStream input = null;
        OutputStream output = null;
        try {
            final File file = new File(target);
            output = new FileOutputStream(file);
            input = connection.getInputStream();

            while ((read = input.read(bytes)) != -1) {
                output.write(bytes, 0, read);
            }
            return file;
        } finally {
            closeQuietly(input);
            closeQuietly(output);
        }
    }

    public static URLConnection connectURL(final String uri, final int timeout) throws Exception {
        final URL url = new URL(uri);
        final URLConnection connection = url.openConnection();
        connection.setConnectTimeout(timeout);
        connection.connect();
        return connection;
    }

}
