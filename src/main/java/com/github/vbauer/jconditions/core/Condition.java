package com.github.vbauer.jconditions.core;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Condition is the core feature of JConditions extension mechanism.
 * All other conditional annotations could be built on top of this annotation.
 *
 * It allows to specify checker that should be run before execution of test method.
 * This checker permits or restricts to run test.
 *
 * See an example here: {@link com.github.vbauer.jconditions.annotation.HasClass}
 *
 * @author Vladislav Bauer
 */

@Target(ElementType.ANNOTATION_TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Condition {

    /**
     * Conditional checker class that will be instantiated and run before test.
     * @return conditional checker class
     */
    @SuppressWarnings("rawtypes")
	Class<? extends ConditionChecker> value();

}
