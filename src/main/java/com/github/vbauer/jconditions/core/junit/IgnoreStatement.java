package com.github.vbauer.jconditions.core.junit;

import com.github.vbauer.jconditions.core.ConditionChecker;
import org.junit.AssumptionViolatedException;
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
        final String conditionClassName = getConditionName();
        throw new AssumptionViolatedException("Ignored by " + conditionClassName);
    }


    private String getConditionName() {
        final Class<?> conditionClass = condition.getClass();
        return conditionClass.getSimpleName();
    }

}
