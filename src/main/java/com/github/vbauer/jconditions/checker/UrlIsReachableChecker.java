package com.github.vbauer.jconditions.checker;

import com.github.vbauer.jconditions.annotation.UrlIsReachable;
import com.github.vbauer.jconditions.core.CheckerContext;
import com.github.vbauer.jconditions.core.ConditionChecker;
import com.github.vbauer.jconditions.util.NetUtils;
import com.github.vbauer.jconditions.util.PropUtils;

/**
 * @author Vladislav Bauer
 */

public class UrlIsReachableChecker implements ConditionChecker<UrlIsReachable> {

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isSatisfied(final CheckerContext<UrlIsReachable> context) {
        final UrlIsReachable annotation = context.getAnnotation();
        final String[] urlAddresses = annotation.value();
        final int timeout = annotation.timeout();
        return isReachable(urlAddresses, timeout);
    }


    private boolean isReachable(final String[] urlAddresses, final int timeout) {
        for (final String urlAddress : urlAddresses) {
            final String url = PropUtils.injectProperties(urlAddress);
            if (!isReachable(url, timeout)) {
                return false;
            }
        }
        return urlAddresses.length > 0;
    }

    private boolean isReachable(final String url, final int timeout) {
        try {
            return NetUtils.connectURL(url, timeout) != null;
        } catch (final Exception ex) {
            return false;
        }
    }

}
