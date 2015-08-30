package com.github.vbauer.jconditions.checker;

import com.github.vbauer.jconditions.annotation.HasClass;
import com.github.vbauer.jconditions.core.ConditionChecker;
import com.github.vbauer.jconditions.core.CheckerContext;

/**
 * @author Vladislav Bauer
 */

public class HasClassChecker implements ConditionChecker<HasClass> {

    @Override
    public boolean isSatisfied(final CheckerContext<HasClass> context) throws Exception {
        final HasClass annotation = context.getAnnotation();
        final String[] classNames = annotation.value();
        return hasAllClasses(classNames);
    }


    private boolean hasAllClasses(final String... classNames) throws Exception {
        for (final String className : classNames) {
            final boolean hasClass = Class.forName(className) != null;
            if (!hasClass) {
                return false;
            }
        }
        return true;
    }

}
