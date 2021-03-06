package com.github.vbauer.jconditions.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * @author Vladislav Bauer
 */

public final class ReflexUtils {

    private static final String PACKAGE_JAVA_LANG_ANNOTATION = "java.lang.annotation";


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
        final List<Annotation> result = new ArrayList<>();
        Class<?> current = clazz;

        while (current != Object.class && current != null) {
            final Class<?>[] interfaces = current.getInterfaces();
            for (final Class<?> i : interfaces) {
                result.addAll(findAllAnnotations(i));
            }

            result.addAll(Arrays.asList(current.getAnnotations()));
            current = current.getSuperclass();
        }
        return result;
    }

    public static <T> T instantiate(final Object instance, final Class<T> checkerClass) {
        try {
            return instantiateImpl(instance, checkerClass);
        } catch (final RuntimeException ex) {
            throw ex;
        } catch (final Exception ex) {
            throw new RuntimeException(ex);
        }
    }


    private static <T> T instantiateImpl(final Object instance, final Class<T> clazz) throws Exception {
        if (clazz.isMemberClass() && !Modifier.isStatic(clazz.getModifiers())) {
            return instantiateInnerClass(instance, clazz);
        }
        return instantiateClass(clazz);
    }

    private static <T> T instantiateClass(final Class<T> clazz) throws Exception {
        final Constructor<T> constructor = clazz.getDeclaredConstructor();
        return ReflexUtils.makeAccessible(constructor).newInstance();
    }

    private static <T> T instantiateInnerClass(
        final Object instance, final Class<T> clazz
    ) throws Exception {
        final Class<?> outerClass = clazz.getDeclaringClass();
        final Constructor<T> constructor = clazz.getDeclaredConstructor(outerClass);
        return ReflexUtils.makeAccessible(constructor).newInstance(instance);
    }

}
