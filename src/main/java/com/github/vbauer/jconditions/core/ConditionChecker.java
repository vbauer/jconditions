package com.github.vbauer.jconditions.core;

/**
 * Conditional checker describes a rule which permits or restricts execution of test method.
 *
 * There are some moments that are useful to know about them:
 * <ul>
 *     <li>Checker class could have any kind of access modifier on class/constructor.</li>
 *     <li>Condition checker must have constructor without arguments.</li>
 *     <li>Constructor could have any kind of access modifier.</li>
 * </ul>
 *
 * @param <T> type of context object
 * @author Vladislav Bauer
 */

public interface ConditionChecker<T> {

    /**
     * Checks if execution of test method should be continued or not.
     *
     * @param context input data for checker
     * @return true if execution should be continued and false - otherwise
     * @throws Exception on any error (it is the same as false in return value)
     */
    boolean isSatisfied(CheckerContext<T> context) throws Exception;

}
