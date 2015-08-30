package com.github.vbauer.jconditions.annotation;

import com.github.vbauer.jconditions.checker.AppIsInstalledChecker;
import com.github.vbauer.jconditions.core.Condition;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Vladislav Bauer
 */

@Condition(AppIsInstalledChecker.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.ANNOTATION_TYPE, ElementType.METHOD })
public @interface AppIsInstalled {

    String[] value();

}
