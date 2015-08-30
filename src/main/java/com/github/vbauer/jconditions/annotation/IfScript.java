package com.github.vbauer.jconditions.annotation;

import com.github.vbauer.jconditions.checker.IfScriptChecker;
import com.github.vbauer.jconditions.core.Condition;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Vladislav Bauer
 */

@Condition(IfScriptChecker.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.ANNOTATION_TYPE, ElementType.METHOD })
public @interface IfScript {

    String DEFAULT_JS = "js";


    String[] value();

    String engine() default DEFAULT_JS;

}
