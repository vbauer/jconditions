package com.github.vbauer.jconditions.annotation;

import com.github.vbauer.jconditions.checker.IfJavaVersionChecker;
import com.github.vbauer.jconditions.core.Condition;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Vladislav Bauer
 */

@Condition(IfJavaVersionChecker.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.ANNOTATION_TYPE, ElementType.METHOD })
public @interface IfJavaVersion {

    String JAVA_6 = "1.6";
    String JAVA_7 = "1.7";
    String JAVA_8 = "1.8";

    String[] value();

}
