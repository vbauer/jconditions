package com.github.vbauer.jconditions.annotation;

import com.github.vbauer.jconditions.checker.SocketIsOpenedChecker;
import com.github.vbauer.jconditions.core.Condition;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Checks that specified socket is opened.
 *
 * @author Vladislav Bauer
 */

@Documented
@Condition(SocketIsOpenedChecker.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.ANNOTATION_TYPE, ElementType.TYPE, ElementType.METHOD})
public @interface SocketIsOpened {

    /**
     * Default value for host.
     */
    String DEFAULT_HOST = "0.0.0.0";

    /**
     * Default value for timeout.
     */
    int DEFAULT_TIMEOUT = 10000;


    /**
     * Host address. Default value: {@link #DEFAULT_HOST}.
     *
     * @return host
     */
    String host() default DEFAULT_HOST;

    /**
     * Port number.
     *
     * @return port
     */
    int port();

    /**
     * Maximum time for connection to the socket. Default value: {@link #DEFAULT_TIMEOUT}.
     *
     * @return timeout
     */
    int timeout() default DEFAULT_TIMEOUT;

}
