package com.github.vbauer.jconditions.misc;

import com.github.vbauer.jconditions.core.CheckerContext;
import com.github.vbauer.jconditions.core.ConditionChecker;

/**
 * @author Vladislav Bauer
 */
public class Never<T> implements ConditionChecker<T> {

    @Override
    public boolean isSatisfied(final CheckerContext<T> context) throws Exception {
        return false;
    }

}
