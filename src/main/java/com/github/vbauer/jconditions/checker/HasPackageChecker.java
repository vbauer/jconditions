package com.github.vbauer.jconditions.checker;

import com.github.vbauer.jconditions.annotation.HasPackage;
import com.github.vbauer.jconditions.core.CheckerContext;
import com.github.vbauer.jconditions.core.ConditionChecker;

/**
 * @author Vladislav Bauer
 */

public class HasPackageChecker implements ConditionChecker<HasPackage> {

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isSatisfied(final CheckerContext<HasPackage> context) throws Exception {
        final HasPackage annotation = context.getAnnotation();
        final String[] packageNames = annotation.value();
        return hasAllPackages(packageNames);
    }


    private boolean hasAllPackages(final String... packageNames) {
        for (final String packageName : packageNames) {
            final boolean hasPackage = Package.getPackage(packageName) != null;
            if (!hasPackage) {
                return false;
            }
        }
        return packageNames.length > 0;
    }

}
