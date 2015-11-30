package com.github.vbauer.jconditions.annotation;

import com.github.vbauer.jconditions.checker.UrlIsReachableChecker;
import com.github.vbauer.jconditions.core.Condition;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Checks that specified URL address is reachable (available via {@link java.net.URLConnection}).
 *
 * @author Vladislav Bauer
 */

@Documented
@Condition(UrlIsReachableChecker.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.ANNOTATION_TYPE, ElementType.TYPE, ElementType.METHOD })
public @interface UrlIsReachable {

    int DEFAULT_TIMEOUT = 10000;


    /**
     * URL address(s) that should be checked.
     * @return URL address(s)
     */
    String[] value();

    /**
     * Maximum timeout for URL connection.
     * @return timeout
     */
    int timeout() default DEFAULT_TIMEOUT;

}
