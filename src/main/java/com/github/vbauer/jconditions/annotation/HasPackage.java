package com.github.vbauer.jconditions.annotation;

import com.github.vbauer.jconditions.checker.HasPackageChecker;
import com.github.vbauer.jconditions.core.Condition;

import java.lang.annotation.*;

/**
 * Checks that specified package(s) is available in classpath.
 *
 * @author Vladislav Bauer
 */

@Documented
@Condition(HasPackageChecker.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.ANNOTATION_TYPE, ElementType.METHOD })
public @interface HasPackage {

    /**
     * Full name of package or packages that should be checked.
     * @return packages
     */
    String[] value();

}
