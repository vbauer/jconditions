package com.github.vbauer.jconditions.util;

/**
 * @author Vladislav Bauer
 */

public final class TextUtils {

    private TextUtils() {
        throw new UnsupportedOperationException();
    }


    public static boolean equalsSafe(final String a, final String b) {
        return (a == null && b == null) || (a != null && a.equals(b));
    }

    public static boolean containsIgnoreCase(final String a, final String b) {
        return a != null && b != null && a.toLowerCase().contains(b.toLowerCase());
    }

}
