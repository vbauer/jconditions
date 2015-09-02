package com.github.vbauer.jconditions.core;

import org.junit.Test;

/**
 * @author Vladislav Bauer
 */

public class ConditionCheckerFactoryTest {

    @Test(expected = RuntimeException.class)
    public void testCreateNegativeRuntimeException() {
        ConditionCheckerFactory.create(null, null);
    }

    @Test(expected = Exception.class)
    public void testCreateNegativeException() {
        ConditionCheckerFactory.create(new Object(), NegativeChecker.class);
    }


    private class NegativeChecker implements ConditionChecker {
        private final boolean value;

        private NegativeChecker(final boolean value) {
            this.value = value;
        }

        @Override
        public boolean isSatisfied(final CheckerContext context) throws Exception {
            return value;
        }
    }

}
