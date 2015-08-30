package com.github.vbauer.jconditions.core;

/**
 * @author Vladislav Bauer
 */

public final class ConditionCheckerExecutor {

    private ConditionCheckerExecutor() {
        throw new UnsupportedOperationException();
    }


    public static boolean isSatisfied(
        final CheckerContext<?> context,
        final Class<? extends ConditionChecker>... checkerClasses
    ) {
        for (final Class<? extends ConditionChecker> checkerClass : checkerClasses) {
            if (!ConditionCheckerExecutor.isSatisfied(context, checkerClass)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isSatisfied(
        final CheckerContext context,
        final Class<? extends ConditionChecker> checkerClass
    ) {
        final Object instance = context.getInstance();
        final ConditionChecker checker = ConditionCheckerFactory.create(instance, checkerClass);
        return isSatisfied(context, checker);
    }

    @SuppressWarnings("unchecked")
    public static boolean isSatisfied(
        final CheckerContext context, final ConditionChecker checker
    ) {
        try {
            return checker.isSatisfied(context);
        } catch (final Exception ex) {
            return false;
        }
    }

}
