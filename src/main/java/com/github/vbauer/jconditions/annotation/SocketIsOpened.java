package com.github.vbauer.jconditions.annotation;

import com.github.vbauer.jconditions.checker.SocketIsOpenedChecker;
import com.github.vbauer.jconditions.core.Condition;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Vladislav Bauer
 */

@Condition(SocketIsOpenedChecker.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.ANNOTATION_TYPE, ElementType.METHOD })
public @interface SocketIsOpened {

    String DEFAULT_HOST = "0.0.0.0";
    int DEFAULT_TIMEOUT = 10000;


    String host() default DEFAULT_HOST;

    int port();

    int timeout() default DEFAULT_TIMEOUT;

}
