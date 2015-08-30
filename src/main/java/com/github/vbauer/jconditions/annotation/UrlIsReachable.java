package com.github.vbauer.jconditions.annotation;

import com.github.vbauer.jconditions.checker.UrlIsReachableChecker;
import com.github.vbauer.jconditions.core.Condition;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Vladislav Bauer
 */

@Condition(UrlIsReachableChecker.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.ANNOTATION_TYPE, ElementType.METHOD })
public @interface UrlIsReachable {

    int DEFAULT_TIMEOUT = 10000;


    String[] value();

    int timeout() default DEFAULT_TIMEOUT;

}
