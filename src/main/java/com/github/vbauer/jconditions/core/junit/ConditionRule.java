package com.github.vbauer.jconditions.core.junit;

import com.github.vbauer.jconditions.core.ConditionChecker;
import com.github.vbauer.jconditions.core.ConditionCheckerEngine;
import org.junit.rules.MethodRule;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;

/**
 * @author Vladislav Bauer
 */

public class ConditionRule implements MethodRule {

    @Override
    public Statement apply(
        final Statement base, final FrameworkMethod method, final Object target
    ) {
        final ConditionChecker<?> checker =
            ConditionCheckerEngine.detectFailedChecker(target, method);

        if (checker != null) {
            return new IgnoreStatement(checker);
        }
        return base;
    }

}
