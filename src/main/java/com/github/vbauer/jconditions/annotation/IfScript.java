package com.github.vbauer.jconditions.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.Callable;

import com.github.vbauer.jconditions.checker.IfScriptChecker;
import com.github.vbauer.jconditions.core.Condition;

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

    /**
     * It is possible to add some data in script execution using an extra context provider.
     * Data will be available in script as "context" parameter.
     * @return class of content provider
     */
    @SuppressWarnings("rawtypes")
	Class<? extends Callable> context() default Callable.class;

}
