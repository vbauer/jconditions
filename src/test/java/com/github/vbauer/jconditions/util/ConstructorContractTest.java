package com.github.vbauer.jconditions.util;

import com.github.vbauer.jconditions.core.ConditionCheckerEngine;
import com.github.vbauer.jconditions.core.ConditionCheckerExecutor;
import com.github.vbauer.jconditions.core.ConditionCheckerFactory;
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
                ConditionCheckerFactory.class,
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

}
