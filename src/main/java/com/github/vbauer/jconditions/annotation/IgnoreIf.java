package com.github.vbauer.jconditions.annotation;

import com.github.vbauer.jconditions.checker.IgnoreIfChecker;
import com.github.vbauer.jconditions.core.Condition;
import com.github.vbauer.jconditions.core.ConditionChecker;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Allows to skip some test method using specific {@link ConditionChecker} class.
 * It will skip test, if checker return true and execute method otherwise.
 * "ConditionChecker" could be separate class, or nested static class, or even inner class.
 * It also works fine with private classes.
 *
 * @author Vladislav Bauer
 */

@Documented
@Condition(IgnoreIfChecker.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.ANNOTATION_TYPE, ElementType.TYPE, ElementType.METHOD })
public @interface IgnoreIf {

    /**
     * Condition checkers that should be checked.
     * @return condition checkers
     */
    @SuppressWarnings("rawtypes")
	Class<? extends ConditionChecker>[] value();

}
