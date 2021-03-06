package com.github.vbauer.jconditions.checker;

import com.github.vbauer.jconditions.annotation.RunIf;
import com.github.vbauer.jconditions.core.CheckerContext;
import com.github.vbauer.jconditions.core.ConditionChecker;
import com.github.vbauer.jconditions.core.ConditionCheckerExecutor;

/**
 * @author Vladislav Bauer
 */

public class RunIfChecker implements ConditionChecker<RunIf> {

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isSatisfied(final CheckerContext<RunIf> context) {
        final RunIf annotation = context.getAnnotation();

        @SuppressWarnings("rawtypes")
        final Class<? extends ConditionChecker>[] checkerClasses = annotation.value();

        //noinspection unchecked
        return ConditionCheckerExecutor.isSatisfied(context, checkerClasses);
    }

}
