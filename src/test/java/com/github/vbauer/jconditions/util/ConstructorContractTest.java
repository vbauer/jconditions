package com.github.vbauer.jconditions.util;

import com.github.vbauer.jconditions.core.CheckerContext;
import com.github.vbauer.jconditions.core.ConditionChecker;
import com.github.vbauer.jconditions.core.ConditionCheckerEngine;
import com.github.vbauer.jconditions.core.ConditionCheckerExecutor;
import com.pushtorefresh.private_constructor_checker.PrivateConstructorChecker;
import org.junit.Test;

/**
 * @author Vladislav Bauer
 */

public class ConstructorContractTest {

    @Test
    public void testConstructors() {
        PrivateConstructorChecker
            .forClasses(
                ConditionCheckerEngine.class,
                ConditionCheckerExecutor.class,
                FSUtils.class,
                InOutUtils.class,
                NetUtils.class,
                PropUtils.class,
                ReflexUtils.class,
                ScriptUtils.class,
                TextUtils.class
            )
            .expectedTypeOfException(UnsupportedOperationException.class)
            .check();
    }

    @Test(expected = RuntimeException.class)
    public void testInstantiateNegativeRuntimeException() {
        ReflexUtils.instantiate(null, null);
    }

    @Test(expected = Exception.class)
    public void testInstantiateNegativeException() {
        ReflexUtils.instantiate(new Object(), NegativeChecker.class);
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
