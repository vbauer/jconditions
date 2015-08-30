package com.github.vbauer.jconditions.core;

/**
 * @param <T> type of context object
 * @author Vladislav Bauer
 */

public interface ConditionChecker<T> {

    boolean isSatisfied(CheckerContext<T> context) throws Exception;

}
