package com.github.vbauer.jconditions.annotation;

import com.github.vbauer.jconditions.checker.IfJavaVersionChecker;
import com.github.vbauer.jconditions.core.Condition;

import java.lang.annotation.*;

/**
 * Checks that test is run on the specific version(s) of JVM.
 *
 * @author Vladislav Bauer
 */

@Documented
@Condition(IfJavaVersionChecker.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.ANNOTATION_TYPE, ElementType.TYPE, ElementType.METHOD })
public @interface IfJavaVersion {

    String JAVA_6 = "1.6";
    String JAVA_7 = "1.7";
    String JAVA_8 = "1.8";
    String JAVA_9 = "1.9";

    /**
     * Version or versions of JVM that should be checked.
     * @return java version
     */
    String[] value();

}
