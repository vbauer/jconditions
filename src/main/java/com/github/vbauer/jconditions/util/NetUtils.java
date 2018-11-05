package com.github.vbauer.jconditions.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author Vladislav Bauer
 */

public final class NetUtils {

    private static final String HTTP_PREFIX = "http://";


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

    public static File copyURLContentToFile(final URLConnection connection, final String target) throws Exception {
        InputStream input = null;
        OutputStream output = null;

        try {
            final File file = new File(target);
            output = new FileOutputStream(file);
            input = connection.getInputStream();
            InOutUtils.copy(input, output);
            return file;
        } finally {
            InOutUtils.closeQuietly(input);
            InOutUtils.closeQuietly(output);
        }
    }

    public static URLConnection connectURL(final String uri, final int timeout) throws IOException {
        final URL url = new URL(uri);
        final URLConnection connection = url.openConnection();
        connection.setConnectTimeout(timeout);
        connection.connect();
        return connection;
    }

}
