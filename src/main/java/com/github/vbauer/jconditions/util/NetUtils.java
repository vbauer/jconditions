package com.github.vbauer.jconditions.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author Vladislav Bauer
 */

public final class NetUtils {

    public static final int BUFFER_SIZE = 1024;
    public static final String HTTP_PREFIX = "http://";


    private NetUtils() {
        throw new UnsupportedOperationException();
    }


    public static String fixScheme(final String address) {
        if (address != null) {
            final URI uri = URI.create(address);
            final String scheme = uri.getScheme();

            if (scheme == null) {
                return HTTP_PREFIX + address;
            }
        }
        return address;
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
            InOutUtils.closeQuietly(input);
            InOutUtils.closeQuietly(output);
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
