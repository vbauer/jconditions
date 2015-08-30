package com.github.vbauer.jconditions.core;

import com.github.vbauer.jconditions.core.ConditionChecker;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Vladislav Bauer
 */

@Target(ElementType.ANNOTATION_TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Condition {

  Class<? extends ConditionChecker> value();

}
