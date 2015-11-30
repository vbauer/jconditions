package com.github.vbauer.jconditions.annotation;

import com.github.vbauer.jconditions.checker.ExistsOnFSChecker;
import com.github.vbauer.jconditions.core.Condition;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Checks that specified file or directory is existed on file system.
 *
 * @author Vladislav Bauer
 */

@Documented
@Condition(ExistsOnFSChecker.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.ANNOTATION_TYPE, ElementType.TYPE, ElementType.METHOD})
public @interface ExistsOnFS {

    /**
     * Files or directories that should be checked.
     * Parameter depends on {@link #type} value.
     *
     * @return files or directories
     */
    String[] value();

    /**
     * Type(s) of FS element(s).
     *
     * @return type
     */
    Type[] type() default Type.FILE;


    /**
     * Type of FS element.
     *
     * @author Vladislav Bauer
     */
    enum Type {
        FILE,
        DIRECTORY,
        SYMLINK
    }

}
