package com.github.vbauer.jconditions.annotation;

import com.github.vbauer.jconditions.checker.IfJavaVersionChecker;
import com.github.vbauer.jconditions.core.Condition;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Checks that test is run on the specific version(s) of JVM.
 *
 * @author Vladislav Bauer
 */

@Documented
@Condition(IfJavaVersionChecker.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.ANNOTATION_TYPE, ElementType.TYPE, ElementType.METHOD})
public @interface IfJavaVersion {

    String JAVA_8 = "1.8";
    String JAVA_9 = "1.9";

    /**
     * Version or versions of JVM that should be checked.
     *
     * @return java version
     */
    String[] value();

}
