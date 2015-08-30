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


    public static ConditionChecker detectFailedChecker(
        final Object instance, final Annotation... annotations
    ) {
        return findCheckerByAnnotations(instance, null, annotations);
    }


    private static ConditionChecker findCheckerByAnnotations(
        final Object instance, final Annotation parent, final Annotation... annotations
    ) {
        for (final Annotation annotation : annotations) {
            if (!ReflexUtils.isInJavaLangAnnotationPackage(annotation)) {
                final Class<? extends Annotation> annotationType = annotation.annotationType();
                if (annotationType == Condition.class) {
                    final Condition condition = (Condition) annotation;
                    final ConditionChecker checker = findCheckerByCondition(instance, parent, condition);
                    if (checker != null) {
                        return checker;
                    }
                }

                final Annotation[] extra = annotationType.getAnnotations();
                final ConditionChecker checker = findCheckerByAnnotations(instance, annotation, extra);
                if (checker != null) {
                    return checker;
                }
            }
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    private static ConditionChecker findCheckerByCondition(
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
