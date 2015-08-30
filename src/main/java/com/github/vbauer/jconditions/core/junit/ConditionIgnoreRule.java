package com.github.vbauer.jconditions.core.junit;

import com.github.vbauer.jconditions.core.ConditionChecker;
import com.github.vbauer.jconditions.core.ConditionCheckerEngine;
import org.junit.rules.MethodRule;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;

import java.lang.annotation.Annotation;

/**
 * @author Vladislav Bauer
 */

public class ConditionIgnoreRule implements MethodRule {

    @Override
    public Statement apply(
        final Statement base, final FrameworkMethod method, final Object target
    ) {
        final Annotation[] annotations = method.getAnnotations();
        final ConditionChecker checker =
            ConditionCheckerEngine.detectChecker(target, annotations);

        if (checker != null) {
            return new IgnoreStatement(checker);
        }
        return base;
    }

}
