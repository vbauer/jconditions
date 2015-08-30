package com.github.vbauer.jconditions.annotation;

import com.github.vbauer.jconditions.checker.RunningOnOSChecker;
import com.github.vbauer.jconditions.core.Condition;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Vladislav Bauer
 */

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

    String[] value();

}
