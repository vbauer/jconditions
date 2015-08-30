package com.github.vbauer.jconditions.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;

/**
 * @author Vladislav Bauer
 */

public final class ReflexUtils {

    public static final String PACKAGE_JAVA_LANG_ANNOTATION = "java.lang.annotation";


    private ReflexUtils() {
        throw new UnsupportedOperationException();
    }


    public static boolean isInJavaLangAnnotationPackage(final Annotation annotation) {
        final Class<? extends Annotation> annotationType = annotation.annotationType();
        final String annotationTypeName = annotationType.getName();
        return annotationTypeName.startsWith(PACKAGE_JAVA_LANG_ANNOTATION);
    }

    @SuppressWarnings("unchecked")
    public static <T> T getFieldValue(final Object object, final String fieldName) {
        try {
            final Class<?> objectClass = object.getClass();
            final Field field = objectClass.getDeclaredField(fieldName);
            field.setAccessible(true);
            return (T) field.get(object);
        } catch (final Exception ex) {
            return null;
        }
    }

    public static <T extends AccessibleObject> T makeAccessible(final T object) {
        if (!object.isAccessible()) {
            object.setAccessible(true);
        }
        return object;
    }

}
