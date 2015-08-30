package com.github.vbauer.jconditions.misc;

import com.github.vbauer.jconditions.core.ConditionChecker;
import com.github.vbauer.jconditions.core.CheckerContext;

/**
 * @author Vladislav Bauer
 */

public class Always implements ConditionChecker {

    @Override
    public boolean isSatisfied(final CheckerContext context) {
        return true;
    }

}
