package com.github.vbauer.jconditions.core;

import com.github.vbauer.jconditions.util.ReflexUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

/**
 * @author Vladislav Bauer
 */

public final class ConditionCheckerFactory {

    private ConditionCheckerFactory() {
        throw new UnsupportedOperationException();
    }


    public static ConditionChecker create(
        final Object instance, final Class<? extends ConditionChecker> checkerClass
    ) {
        try {
            return ConditionCheckerFactory.createImpl(instance, checkerClass);
        } catch (final RuntimeException ex) {
            throw ex;
        } catch (final Exception ex) {
            throw new RuntimeException(ex);
        }
    }


    private static ConditionChecker createImpl(
        final Object instance, final Class<? extends ConditionChecker> conditionClass
    ) throws Exception {
        if (conditionClass.isMemberClass() && !Modifier.isStatic(conditionClass.getModifiers())) {
            return instantiateInnerClass(instance, conditionClass);
        }
        return instantiateClass(conditionClass);
    }

    private static ConditionChecker instantiateClass(
        final Class<? extends ConditionChecker> conditionClass
    ) throws Exception {
        final Constructor<? extends ConditionChecker> constructor =
            conditionClass.getDeclaredConstructor();
        return ReflexUtils.makeAccessible(constructor).newInstance();
    }

    private static ConditionChecker instantiateInnerClass(
        final Object instance, final Class<? extends ConditionChecker> conditionClass
    ) throws Exception {
        final Class<?> clazz = conditionClass.getDeclaringClass();
        final Class<?>[] parameterTypes = { clazz };
        final Constructor<? extends ConditionChecker> constructor =
            conditionClass.getDeclaredConstructor(parameterTypes);
        return ReflexUtils.makeAccessible(constructor).newInstance(instance);
    }

}
