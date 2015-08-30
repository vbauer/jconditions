package com.github.vbauer.jconditions.util;

import java.io.File;

/**
 * @author Vladislav Bauer
 */

public final class FSUtils {

    private FSUtils() {
        throw new UnsupportedOperationException();
    }


    public static boolean fileExists(final String path) {
        final File file = new File(path);
        return file.exists() && file.isFile();
    }

    public static boolean directoryExists(final String path) {
        final File file = new File(path);
        return file.exists() && file.isDirectory();
    }

    public static boolean deleteFile(final String path) {
        final File file = new File(path);
        return file.delete();
    }

}
