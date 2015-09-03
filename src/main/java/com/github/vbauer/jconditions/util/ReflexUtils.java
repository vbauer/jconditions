package com.github.vbauer.jconditions.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.util.*;

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

    public static Collection<Annotation> findAllAnnotations(final Class<?> clazz) {
        final List<Annotation> result = new ArrayList<Annotation>();
        Class<?> current = clazz;

        while (current != Object.class && current != null) {
            final Class<?>[] interfaces = current.getInterfaces();
            for (final Class<?> i : interfaces) {
                result.addAll(findAllAnnotations(i));
            }

            final Class<?> superclass = current.getSuperclass();
            result.addAll(Arrays.asList(current.getAnnotations()));
            result.addAll(findAllAnnotations(superclass));
            current = superclass;
        }
        return result;
    }

}
