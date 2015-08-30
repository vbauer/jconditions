package com.github.vbauer.jconditions.annotation;

import com.github.vbauer.jconditions.checker.ResourceIsAvailableChecker;
import com.github.vbauer.jconditions.core.Condition;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Vladislav Bauer
 */

@Condition(ResourceIsAvailableChecker.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.ANNOTATION_TYPE, ElementType.METHOD })
public @interface ResourceIsAvailable {

    int DEFAULT_TIMEOUT = 10000;


    String source();

    String target();

    boolean cache() default true;

    int timeout() default DEFAULT_TIMEOUT;

}
