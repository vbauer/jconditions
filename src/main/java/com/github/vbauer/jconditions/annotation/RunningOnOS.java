package com.github.vbauer.jconditions.annotation;

import com.github.vbauer.jconditions.checker.RunningOnOSChecker;
import com.github.vbauer.jconditions.core.Condition;

import java.lang.annotation.*;

/**
 * Checks the current operation system and runs test method only when it is specified and `value` parameter.
 *
 * @author Vladislav Bauer
 */

@Documented
@Condition(RunningOnOSChecker.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.ANNOTATION_TYPE, ElementType.METHOD })
public @interface RunningOnOS {

    String MAC = "mac";
    String MAC_OSX = "mac os x";
    String LINUX = "linux";
    String WINDOWS = "win";
    String OS2 = "os/2";
    String AIX = "aix";
    String HP_UX = "hp-ux";
    String IRIX = "irix";
    String SOLARIS = "solaris";
    String SUN_OS = "sunos";

    /**
     * Operation system(s) that should be checked.
     * @return OS name
     */
    String[] value();

}
