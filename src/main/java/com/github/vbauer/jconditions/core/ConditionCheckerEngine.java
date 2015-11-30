package com.github.vbauer.jconditions.core;

import com.github.vbauer.jconditions.util.ReflexUtils;
import org.junit.runners.model.FrameworkMethod;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.Collection;

/**
 * @author Vladislav Bauer
 */

public final class ConditionCheckerEngine {

    private ConditionCheckerEngine() {
        throw new UnsupportedOperationException();
    }


    public static ConditionChecker<?> detectFailedChecker(
        final Object instance, final FrameworkMethod method
    ) {
        final Collection<Annotation> annotations = findAllAnnotations(instance, method);
        return findCheckerByAnnotations(instance, null, annotations);
    }


    private static ConditionChecker<?> findCheckerByAnnotations(
        final Object instance, final Annotation parent, final Collection<Annotation> annotations
    ) {
        for (final Annotation annotation : annotations) {
            final ConditionChecker<?> checker =
                getConditionChecker(instance, parent, annotation);

            if (checker != null) {
                return checker;
            }
        }
        return null;
    }

    private static ConditionChecker<?> getConditionChecker(
        final Object instance, final Annotation parent, final Annotation annotation
    ) {
        if (!ReflexUtils.isInJavaLangAnnotationPackage(annotation)) {
            final Class<? extends Annotation> annotationType = annotation.annotationType();

            if (annotationType == Condition.class) {
                final Condition condition = (Condition) annotation;
                final ConditionChecker<?> checker =
                    findCheckerByCondition(instance, parent, condition);
                if (checker != null) {
                    return checker;
                }
            }

            final Collection<Annotation> extra =
                Arrays.asList(annotationType.getAnnotations());

            final ConditionChecker<?> checker =
                findCheckerByAnnotations(instance, annotation, extra);

            if (checker != null) {
                return checker;
            }
        }
        return null;
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    private static <T> ConditionChecker<T> findCheckerByCondition(
        final T instance, final Annotation parent, final Condition condition
    ) {
        final Class<? extends ConditionChecker> checkerClass = condition.value();
        final ConditionChecker<T> checker = ReflexUtils.instantiate(instance, checkerClass);
		final CheckerContext<T> context = new CheckerContext(instance, parent);

        if (!ConditionCheckerExecutor.isSatisfied(context, checker)) {
            return checker;
        }
        return null;
    }

    private static Collection<Annotation> findAllAnnotations(
        final Object instance, final FrameworkMethod method
    ) {
        final Collection<Annotation> result = ReflexUtils.findAllAnnotations(instance.getClass());
        result.addAll(Arrays.asList(method.getAnnotations()));
        return result;
    }

}
