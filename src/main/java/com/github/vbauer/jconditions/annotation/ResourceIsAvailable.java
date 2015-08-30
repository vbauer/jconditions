package com.github.vbauer.jconditions.annotation;

import com.github.vbauer.jconditions.checker.ResourceIsAvailableChecker;
import com.github.vbauer.jconditions.core.Condition;

import java.lang.annotation.*;

/**
 * Allows to minimize code which is necessary to download some document/file via HTTP/HTTPS.
 * It is also possible to cache downloaded resource between test executions, otherwise it will be remove after test.
 *
 * @author Vladislav Bauer
 */

@Documented
@Condition(ResourceIsAvailableChecker.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.ANNOTATION_TYPE, ElementType.METHOD })
public @interface ResourceIsAvailable {

    int DEFAULT_TIMEOUT = 10000;


    /**
     * HTTP/HTTPS file or document.
     * @return url address of the resource
     */
    String source();

    /**
     * Path to file where content should be saved.
     * @return file path
     */
    String target();

    /**
     * Flag to configure cache option.
     * @return true if it is necessary to use cache, and false otherwise.
     */
    boolean cache() default true;

    /**
     * Maximum time in ms for connection to the `source`.
     * @return timeout
     */
    int timeout() default DEFAULT_TIMEOUT;

}
