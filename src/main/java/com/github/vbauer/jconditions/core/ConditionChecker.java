package com.github.vbauer.jconditions.core;

import com.github.vbauer.jconditions.core.CheckerContext;

/**
 * @author Vladislav Bauer
 */

public interface ConditionChecker<T> {

    boolean isSatisfied(CheckerContext<T> context) throws Exception;

}
