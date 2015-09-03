package com.github.vbauer.jconditions.util;

import java.io.File;
import java.io.IOException;

/**
 * @author Vladislav Bauer
 */

public final class FSUtils {

    private FSUtils() {
        throw new UnsupportedOperationException();
    }


    public static boolean exists(final String path) {
        final File file = new File(path);
        return file.exists();
    }

    public static boolean fileExists(final String path) {
        final File file = new File(path);
        return file.exists() && file.isFile();
    }

    public static boolean directoryExists(final String path) {
        final File file = new File(path);
        return file.exists() && file.isDirectory();
    }

    public static boolean isSymlink(final String path) throws IOException {
        final File file = new File(path);
        final File fileInCanonicalDir;
        if (file.getParent() == null) {
            fileInCanonicalDir = file;
        } else {
            fileInCanonicalDir = new File(file.getParentFile().getCanonicalFile(), file.getName());
        }
        return !fileInCanonicalDir.getCanonicalFile().equals(fileInCanonicalDir.getAbsoluteFile());
    }


    public static boolean deleteFile(final String path) {
        final File file = new File(path);
        return file.delete();
    }

}
