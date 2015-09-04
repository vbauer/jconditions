package com.github.vbauer.jconditions.util;

import java.io.Closeable;

/**
 * @author Vladislav Bauer
 */

public final class InOutUtils {

    private InOutUtils() {
        throw new UnsupportedOperationException();
    }


    public static boolean closeQuietly(final Closeable closeable) {
        try {
            closeable.close();
            return true;
        } catch (final Exception ex) {
            return false;
        }
    }

}
