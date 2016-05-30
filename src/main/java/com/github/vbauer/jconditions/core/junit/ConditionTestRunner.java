package com.github.vbauer.jconditions.core.junit;

import com.github.vbauer.jconditions.core.ConditionChecker;
import com.github.vbauer.jconditions.core.ConditionCheckerEngine;
import com.github.vbauer.jconditions.util.ReflexUtils;
import org.junit.internal.runners.statements.InvokeMethod;
import org.junit.runner.Description;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;

/**
 * @author Vladislav Bauer
 */

public class ConditionTestRunner extends BlockJUnit4ClassRunner {

    public ConditionTestRunner(final Class<?> clazz) throws InitializationError {
        super(clazz);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    protected void runChild(final FrameworkMethod method, final RunNotifier notifier) {
        final Description description = describeChild(method);
        if (isIgnored(method)) {
            notifier.fireTestIgnored(description);
        } else {
            final InvokeMethod statement = (InvokeMethod) methodBlock(method);
            final Object test = ReflexUtils.getFieldValue(statement, "target");

            final ConditionChecker<?> checker =
                ConditionCheckerEngine.detectFailedChecker(test, method);

            if (checker != null) {
                notifier.fireTestIgnored(description);
            } else {
                runLeaf(statement, description, notifier);
            }
        }
    }

}
