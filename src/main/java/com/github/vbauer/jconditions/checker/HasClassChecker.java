package com.github.vbauer.jconditions.checker;

import com.github.vbauer.jconditions.annotation.HasClass;
import com.github.vbauer.jconditions.core.CheckerContext;
import com.github.vbauer.jconditions.core.ConditionChecker;

/**
 * @author Vladislav Bauer
 */

public class HasClassChecker implements ConditionChecker<HasClass> {

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isSatisfied(final CheckerContext<HasClass> context) throws Exception {
        final HasClass annotation = context.getAnnotation();
        final String[] classNames = annotation.value();
        return hasAllClasses(classNames);
    }


    private boolean hasAllClasses(final String... classNames) {
        for (final String className : classNames) {
            final boolean hasClass = hasClass(className);
            if (!hasClass) {
                return false;
            }
        }
        return classNames.length > 0;
    }

    private boolean hasClass(final String className) {
        try {
            return Class.forName(className) != null;
        } catch (final Exception ex) {
            return false;
        }
    }

}
