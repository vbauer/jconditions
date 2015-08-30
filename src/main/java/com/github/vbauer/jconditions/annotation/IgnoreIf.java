package com.github.vbauer.jconditions.annotation;

import com.github.vbauer.jconditions.checker.IgnoreIfChecker;
import com.github.vbauer.jconditions.core.Condition;
import com.github.vbauer.jconditions.core.ConditionChecker;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Vladislav Bauer
 */

@Condition(IgnoreIfChecker.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.ANNOTATION_TYPE, ElementType.METHOD })
public @interface IgnoreIf {

    Class<? extends ConditionChecker>[] value();

}
