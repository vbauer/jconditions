package com.github.vbauer.jconditions.core;

import com.github.vbauer.jconditions.util.ReflexUtils;

/**
 * @author Vladislav Bauer
 */

public final class ConditionCheckerExecutor {

    private ConditionCheckerExecutor() {
        throw new UnsupportedOperationException();
    }


    @SuppressWarnings({ "rawtypes" })
	public static boolean isSatisfied(
        final CheckerContext<?> context,
        final Class<? extends ConditionChecker>... checkerClasses
    ) {
        for (final Class<? extends ConditionChecker> checkerClass : checkerClasses) {
            if (!isSatisfied(context, checkerClass)) {
                return false;
            }
        }
        return true;
    }

    @SuppressWarnings("rawtypes")
	public static boolean isSatisfied(
        final CheckerContext context,
        final Class<? extends ConditionChecker> checkerClass
    ) {
        final Object instance = context.getInstance();
        final ConditionChecker checker = ReflexUtils.instantiate(instance, checkerClass);
        return isSatisfied(context, checker);
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static boolean isSatisfied(
        final CheckerContext context, final ConditionChecker checker
    ) {
        try {
            return checker.isSatisfied(context);
        } catch (final Throwable ex) {
            return false;
        }
    }

}
