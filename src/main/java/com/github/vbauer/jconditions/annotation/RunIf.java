package com.github.vbauer.jconditions.annotation;

import com.github.vbauer.jconditions.checker.RunIfChecker;
import com.github.vbauer.jconditions.core.Condition;
import com.github.vbauer.jconditions.core.ConditionChecker;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * It is an opposite annotation to {@link IgnoreIf}.
 * It will run test method if {@link ConditionChecker} returns `true`.
 *
 * @author Vladislav Bauer
 */

@Documented
@Condition(RunIfChecker.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.ANNOTATION_TYPE, ElementType.TYPE, ElementType.METHOD })
public @interface RunIf {

    /**
     * Condition checkers that should be checked.
     * @return condition checkers
     */
    @SuppressWarnings("rawtypes")
	Class<? extends ConditionChecker>[] value();

}
