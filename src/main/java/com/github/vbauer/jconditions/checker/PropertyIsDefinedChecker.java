package com.github.vbauer.jconditions.checker;

import com.github.vbauer.jconditions.annotation.PropertyIsDefined;
import com.github.vbauer.jconditions.core.CheckerContext;
import com.github.vbauer.jconditions.core.ConditionChecker;
import com.github.vbauer.jconditions.util.PropUtils;
import com.github.vbauer.jconditions.util.TextUtils;

/**
 * @author Vladislav Bauer
 */

public class PropertyIsDefinedChecker implements ConditionChecker<PropertyIsDefined> {

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isSatisfied(final CheckerContext<PropertyIsDefined> context) {
        final PropertyIsDefined annotation = context.getAnnotation();
        final String[] keys = annotation.keys();
        final String[] values = annotation.values();

        return isSatisfied(keys, values);
    }


    private boolean isSatisfied(final String[] keys, final String[] values) {
        int index = 0;
        for (final String key : keys) {
            final String variable = PropUtils.getSystemProperty(
                PropUtils.injectProperties(key)
            );

            try {
                final String value = PropUtils.injectProperties(values[index++]);
                if (!TextUtils.equalsSafe(variable, value)) {
                    return false;
                }
            } catch (final IndexOutOfBoundsException ex) {
                if (variable == null) {
                    return false;
                }
            }
        }
        return keys.length > 0;
    }

}
