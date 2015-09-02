package com.github.vbauer.jconditions.util;

import java.net.URI;

/**
 * @author Vladislav Bauer
 */

public final class NetUtils {

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

}
