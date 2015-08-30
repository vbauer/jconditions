package com.github.vbauer.jconditions.checker;

import com.github.vbauer.jconditions.annotation.UrlIsReachable;
import com.github.vbauer.jconditions.core.CheckerContext;
import com.github.vbauer.jconditions.core.ConditionChecker;
import com.github.vbauer.jconditions.util.InOutUtils;
import com.github.vbauer.jconditions.util.PropUtils;

/**
 * @author Vladislav Bauer
 */

public class UrlIsReachableChecker implements ConditionChecker<UrlIsReachable> {

    @Override
    public boolean isSatisfied(final CheckerContext<UrlIsReachable> context) throws Exception {
        final UrlIsReachable annotation = context.getAnnotation();
        final String[] urlAddresses = annotation.value();
        final int timeout = annotation.timeout();
        return isReachable(urlAddresses, timeout);
    }


    private boolean isReachable(final String[] urlAddresses, final int timeout) throws Exception {
        for (final String urlAddress : urlAddresses) {
            final String url = PropUtils.injectProperties(urlAddress);
            final boolean reachable = InOutUtils.connectURL(url, timeout) != null;
            if (!reachable) {
                return false;
            }
        }
        return true;
    }

}
