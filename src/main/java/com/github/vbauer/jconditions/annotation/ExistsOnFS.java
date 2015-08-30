package com.github.vbauer.jconditions.annotation;

import com.github.vbauer.jconditions.checker.ExistsOnFSChecker;
import com.github.vbauer.jconditions.core.Condition;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Vladislav Bauer
 */

@Condition(ExistsOnFSChecker.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.ANNOTATION_TYPE, ElementType.METHOD })
public @interface ExistsOnFS {

    String[] value();

    Type type() default Type.FILE;

    /**
     * @author Vladislav Bauer
     */
    enum Type {
        FILE,
        DIRECTORY
    }

}
