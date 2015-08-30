package com.github.vbauer.jconditions.core;

import com.github.vbauer.jconditions.util.ReflexUtils;

import java.lang.annotation.Annotation;

/**
 * @author Vladislav Bauer
 */

public final class ConditionCheckerEngine {
    
    private ConditionCheckerEngine() {
        throw new UnsupportedOperationException();
    }


    public static ConditionChecker detectChecker(
        final Object instance, final Annotation... annotations
    ) {
        return findChecker(instance, null, annotations);
    }

    private static ConditionChecker findChecker(
        final Object instance, final Annotation parent, final Annotation... annotations
    ) {
        for (final Annotation annotation : annotations) {
            if (!ReflexUtils.isInJavaLangAnnotationPackage(annotation)) {
                final Class<? extends Annotation> annotationType = annotation.annotationType();
                if (annotationType == Condition.class) {
                    final Condition condition = (Condition) annotation;
                    final ConditionChecker checker = findChecker(instance, parent, condition);
                    if (checker != null) {
                        return checker;
                    }
                }

                final Annotation[] extra = annotationType.getAnnotations();
                final ConditionChecker checker = findChecker(instance, annotation, extra);
                if (checker != null) {
                    return checker;
                }
            }
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    private static ConditionChecker findChecker(
        final Object instance, final Annotation parent, final Condition condition
    ) {
        final Class<? extends ConditionChecker> checkerClass = condition.value();
        final ConditionChecker checker = ConditionCheckerFactory.create(instance, checkerClass);
        final CheckerContext context = new CheckerContext(instance, parent);

        if (!ConditionCheckerExecutor.isSatisfied(context, checker)) {
            return checker;
        }
        return null;
    }
    
}
