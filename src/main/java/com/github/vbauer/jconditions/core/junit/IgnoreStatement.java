package com.github.vbauer.jconditions.core.junit;

import com.github.vbauer.jconditions.core.ConditionChecker;
import org.junit.Assume;
import org.junit.runners.model.Statement;

/**
 * @author Vladislav Bauer
 */

public class IgnoreStatement extends Statement {

    private final ConditionChecker condition;


    public IgnoreStatement(final ConditionChecker condition) {
        this.condition = condition;
    }


    @Override
    public void evaluate() {
        final Class<? extends ConditionChecker> conditionClass = condition.getClass();
        final String conditionClassName = conditionClass.getSimpleName();
        Assume.assumeTrue("Ignored by " + conditionClassName, false);
    }

}
