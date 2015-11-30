package com.github.vbauer.jconditions.checker;

import com.github.vbauer.jconditions.annotation.IgnoreIf;
import com.github.vbauer.jconditions.core.CheckerContext;
import com.github.vbauer.jconditions.core.ConditionChecker;
import com.github.vbauer.jconditions.core.ConditionCheckerExecutor;

/**
 * @author Vladislav Bauer
 */

public class IgnoreIfChecker implements ConditionChecker<IgnoreIf> {

    @Override
    public boolean isSatisfied(final CheckerContext<IgnoreIf> context) throws Exception {
        final IgnoreIf annotation = context.getAnnotation();

        @SuppressWarnings("rawtypes")
		final Class<? extends ConditionChecker>[] checkerClasses = annotation.value();

        //noinspection unchecked
        return !ConditionCheckerExecutor.isSatisfied(context, checkerClasses);
    }

}
