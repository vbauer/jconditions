package com.github.vbauer.jconditions.annotation;

import com.github.vbauer.jconditions.checker.SocketIsOpenedChecker;
import com.github.vbauer.jconditions.core.Condition;

import java.lang.annotation.*;

/**
 * Checks that specified socket is opened.
 *
 * @author Vladislav Bauer
 */

@Documented
@Condition(SocketIsOpenedChecker.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.ANNOTATION_TYPE, ElementType.TYPE, ElementType.METHOD })
public @interface SocketIsOpened {

    String DEFAULT_HOST = "0.0.0.0";
    int DEFAULT_TIMEOUT = 10000;


    /**
     * Host address.
     * @return host
     */
    String host() default DEFAULT_HOST;

    /**
     * Port number.
     * @return port
     */
    int port();

    /**
     * Maximum time for connection to the socket.
     * @return timeout
     */
    int timeout() default DEFAULT_TIMEOUT;

}
