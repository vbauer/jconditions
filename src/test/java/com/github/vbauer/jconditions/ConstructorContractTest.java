package com.github.vbauer.jconditions;

import com.github.vbauer.jconditions.core.ConditionCheckerEngine;
import com.github.vbauer.jconditions.core.ConditionCheckerExecutor;
import com.github.vbauer.jconditions.core.ConditionCheckerFactory;
import com.github.vbauer.jconditions.util.*;
import com.pushtorefresh.private_constructor_checker.PrivateConstructorChecker;
import org.junit.Test;

/**
 * @author Vladislav Bauer
 */

public class ConstructorContractTest {

    @Test
    public void testConstructors() {
        checkClasses(
            ConditionCheckerEngine.class,
            ConditionCheckerExecutor.class,
            ConditionCheckerFactory.class,
            FSUtils.class,
            InOutUtils.class,
            PropUtils.class,
            ReflexUtils.class,
            ScriptUtils.class,
            TextUtils.class
        );
    }


    private void checkClasses(final Class<?>... classes) {
        for (final Class<?> clazz : classes) {
            PrivateConstructorChecker
                .forClass(clazz)
                .expectedTypeOfException(UnsupportedOperationException.class)
                .check();
        }
    }

}
