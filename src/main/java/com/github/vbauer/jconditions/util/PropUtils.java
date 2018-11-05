package com.github.vbauer.jconditions.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author Vladislav Bauer
 */

public final class PropUtils {

    private static final String VAR_PREFIX = "${";
    private static final String VAR_POSTFIX = "}";


    private PropUtils() {
        throw new UnsupportedOperationException();
    }


    public static String getSystemProperty(final String key) {
        final Map<String, String> properties = getSystemProperties();
        return properties.get(key);
    }

    public static Map<String, String> getSystemProperties() {
        final Map<String, String> result = new HashMap<String, String>();
        result.putAll(System.getenv());
        result.putAll(convertPropertiesToMap(System.getProperties()));
        return result;
    }

    public static Map<String, String> convertPropertiesToMap(final Properties properties) {
        final Map<String, String> result = new HashMap<String, String>();
        for (final String name : properties.stringPropertyNames()) {
            result.put(name, properties.getProperty(name));
        }
        return result;
    }

    public static String injectProperties(final String text) {
        if (text != null && text.contains(VAR_PREFIX)) {
            String result = text;
            final Map<String, String> systemProperties = getSystemProperties();
            for (final Map.Entry<String, String> entry : systemProperties.entrySet()) {
                final String key = entry.getKey();
                final String value = entry.getValue();
                result = result.replace(VAR_PREFIX + key + VAR_POSTFIX, value);
            }
            return result;
        }
        return text;
    }

    public static boolean hasAnyWithProperties(final String value, final String... variants) {
        for (final String operationSystem : variants) {
            final String injected = injectProperties(operationSystem);
            if (TextUtils.containsIgnoreCase(value, injected)) {
                return true;
            }
        }
        return false;
    }

}
