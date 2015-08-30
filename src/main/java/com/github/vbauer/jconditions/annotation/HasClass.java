package com.github.vbauer.jconditions.annotation;

import com.github.vbauer.jconditions.checker.HasClassChecker;
import com.github.vbauer.jconditions.core.Condition;

import java.lang.annotation.*;

/**
 * Checks that specified class(es) is available in classpath.
 *
 * @author Vladislav Bauer
 */

@Documented
@Condition(HasClassChecker.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.ANNOTATION_TYPE, ElementType.METHOD })
public @interface HasClass {

    /**
     * Full names of class or classes that should be checked.
     * @return classes
     */
    String[] value();

}
