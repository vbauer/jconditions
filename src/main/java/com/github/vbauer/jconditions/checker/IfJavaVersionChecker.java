package com.github.vbauer.jconditions.checker;

import com.github.vbauer.jconditions.annotation.IfJavaVersion;
import com.github.vbauer.jconditions.core.CheckerContext;
import com.github.vbauer.jconditions.core.ConditionChecker;
import com.github.vbauer.jconditions.util.PropUtils;

/**
 * @author Vladislav Bauer
 */

public class IfJavaVersionChecker implements ConditionChecker<IfJavaVersion> {

    public static final String PROPERTY_JAVA_VERSION = "java.version";


    @Override
    public boolean isSatisfied(final CheckerContext<IfJavaVersion> context) throws Exception {
        final IfJavaVersion annotation = context.getAnnotation();
        final String[] versions = annotation.value();
        return PropUtils.hasAnyWithProperties(javaVersion(), versions);
    }


    public static String javaVersion() {
        return PropUtils.getSystemProperty(PROPERTY_JAVA_VERSION).toLowerCase();
    }

}
