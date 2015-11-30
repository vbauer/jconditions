package com.github.vbauer.jconditions.annotation;

import com.github.vbauer.jconditions.checker.HasFreeSpaceChecker;
import com.github.vbauer.jconditions.core.Condition;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Checks that disk(s) has available free space.
 *
 * @author Vladislav Bauer
 */

@Documented
@Condition(HasFreeSpaceChecker.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.ANNOTATION_TYPE, ElementType.TYPE, ElementType.METHOD })
public @interface HasFreeSpace {

    /**
     * {@link Long#MIN_VALUE} is the same as undefined.
     */
    long UNDEFINED = Long.MIN_VALUE;


    /**
     * Disk or disks that should be checked (ex: "C:\\").
     * @return element(s) on FS
     */
    String[] value();

    /**
     * Minimum amount of available free space on disk.
     * @return min space in bytes
     */
    long min() default UNDEFINED;

    /**
     * Maximum amount of available free space on disk.
     * @return max space in bytes
     */
    long max() default UNDEFINED;

}
