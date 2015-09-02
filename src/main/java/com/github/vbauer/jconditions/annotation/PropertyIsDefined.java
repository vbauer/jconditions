package com.github.vbauer.jconditions.annotation;

import com.github.vbauer.jconditions.checker.PropertyIsDefinedChecker;
import com.github.vbauer.jconditions.core.Condition;

import java.lang.annotation.*;

/**
 * Checks if environment or system property is defined.
 *
 * @author Vladislav Bauer
 */

@Documented
@Condition(PropertyIsDefinedChecker.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.ANNOTATION_TYPE, ElementType.TYPE, ElementType.METHOD })
public @interface PropertyIsDefined {

    /**
     * Keys of environment or system variables.
     * @return keys
     */
    String[] keys();

    /**
     * Values that should correspond to keys (optional parameter).
     * @return values
     */
    String[] values() default {};

}
