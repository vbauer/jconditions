package com.github.vbauer.jconditions.annotation;

import com.github.vbauer.jconditions.checker.PropertyIsDefinedChecker;
import com.github.vbauer.jconditions.core.Condition;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Vladislav Bauer
 */

@Condition(PropertyIsDefinedChecker.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.ANNOTATION_TYPE, ElementType.METHOD })
public @interface PropertyIsDefined {

    String[] keys();

    String[] values() default {};

}
