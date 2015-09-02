package com.github.vbauer.jconditions.annotation;

import com.github.vbauer.jconditions.checker.IfScriptChecker;
import com.github.vbauer.jconditions.core.Condition;

import java.lang.annotation.*;

/**
 * Allows to write custom conditional rules using JSR 223: Scripting for the JavaTM Platform.
 * JavaScript engine is available by default (it is part of JVM).
 * All other JSR233-compatible languages will be included automatically if they are available in classpath.
 *
 * @author Vladislav Bauer
 */

@Documented
@Condition(IfScriptChecker.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.ANNOTATION_TYPE, ElementType.TYPE, ElementType.METHOD })
public @interface IfScript {

    String DEFAULT_JS = "js";

    /**
     * Script or scripts that should be executed.
     * Return value will be converted to boolean type (even "String" and "Number"s).
     * @return script or scripts
     */
    String[] value();

    /**
     * Type of script engine.
     * Script engine will be detected using this parameter and mime type / name / language.
     * @return engine type.
     */
    String engine() default DEFAULT_JS;

}
