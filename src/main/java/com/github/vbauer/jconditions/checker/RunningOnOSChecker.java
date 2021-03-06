package com.github.vbauer.jconditions.checker;

import com.github.vbauer.jconditions.annotation.RunningOnOS;
import com.github.vbauer.jconditions.core.CheckerContext;
import com.github.vbauer.jconditions.core.ConditionChecker;
import com.github.vbauer.jconditions.util.PropUtils;

/**
 * @author Vladislav Bauer
 */

public class RunningOnOSChecker implements ConditionChecker<RunningOnOS> {

    private static final String PROPERTY_OS_NAME = "os.name";


    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isSatisfied(final CheckerContext<RunningOnOS> context) {
        final RunningOnOS annotation = context.getAnnotation();
        final String[] operationSystems = annotation.value();
        return PropUtils.hasAnyWithProperties(currentOS(), operationSystems);
    }


    private static String currentOS() {
        return PropUtils.getSystemProperty(PROPERTY_OS_NAME).toLowerCase();
    }

}
