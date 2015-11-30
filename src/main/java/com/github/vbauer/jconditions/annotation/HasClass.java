package com.github.vbauer.jconditions.annotation;

import com.github.vbauer.jconditions.checker.HasClassChecker;
import com.github.vbauer.jconditions.core.Condition;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Checks that specified class(es) is available in classpath.
 *
 * @author Vladislav Bauer
 */

@Documented
@Condition(HasClassChecker.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.ANNOTATION_TYPE, ElementType.TYPE, ElementType.METHOD})
public @interface HasClass {

    /**
     * Full names of class or classes that should be checked.
     *
     * @return classes
     */
    String[] value();

}
