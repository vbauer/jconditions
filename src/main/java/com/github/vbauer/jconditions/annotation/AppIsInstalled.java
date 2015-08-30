package com.github.vbauer.jconditions.annotation;

import com.github.vbauer.jconditions.checker.AppIsInstalledChecker;
import com.github.vbauer.jconditions.core.Condition;

import java.lang.annotation.*;

/**
 * Checks that specified application(s) is installed.
 *
 * @author Vladislav Bauer
 */

@Documented
@Condition(AppIsInstalledChecker.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.ANNOTATION_TYPE, ElementType.METHOD })
public @interface AppIsInstalled {

    /**
     * Application or applications that should be checked.
     * @return applications
     */
    String[] value();

}
