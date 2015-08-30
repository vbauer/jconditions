package com.github.vbauer.jconditions.misc;

import com.github.vbauer.jconditions.core.CheckerContext;
import com.github.vbauer.jconditions.core.ConditionChecker;

/**
 * @author Vladislav Bauer
 */
public class Never implements ConditionChecker {

    @Override
    public boolean isSatisfied(final CheckerContext context) throws Exception {
        return false;
    }

}
