package com.github.vbauer.jconditions.checker;

import com.github.vbauer.jconditions.annotation.HasFreeSpace;
import com.github.vbauer.jconditions.core.CheckerContext;
import com.github.vbauer.jconditions.core.ConditionChecker;

import java.io.File;

/**
 * @author Vladislav Bauer
 */

public class HasFreeSpaceChecker implements ConditionChecker<HasFreeSpace> {

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isSatisfied(final CheckerContext<HasFreeSpace> context) throws Exception {
        final HasFreeSpace annotation = context.getAnnotation();
        final String[] targets = annotation.value();
        final long min = annotation.min();
        final long max = annotation.max();

        return isSatisfied(targets, min, max);
    }


    private boolean isSatisfied(final String[] targets, final long min, final long max) {
        for (final String target : targets) {
            if (!isSatisfied(target, max, min)) {
                return false;
            }
        }
        return targets.length > 0;
    }

    private boolean isSatisfied(final String target, final long max, final long min) {
        final File file = new File(target);
        if (file.exists()) {
            final long freeSpace = file.getFreeSpace();
            if (min != HasFreeSpace.UNDEFINED && freeSpace < min) {
                return false;
            } else if (max != HasFreeSpace.UNDEFINED && freeSpace > max) {
                return false;
            }
        }
        return true;
    }

}
