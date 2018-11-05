package com.github.vbauer.jconditions.checker;

import com.github.vbauer.jconditions.annotation.AppIsInstalled;
import com.github.vbauer.jconditions.core.CheckerContext;
import com.github.vbauer.jconditions.core.ConditionChecker;
import com.github.vbauer.jconditions.util.PropUtils;

/**
 * @author Vladislav Bauer
 */

public class AppIsInstalledChecker implements ConditionChecker<AppIsInstalled> {

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isSatisfied(final CheckerContext<AppIsInstalled> context) throws Exception {
        final AppIsInstalled annotation = context.getAnnotation();
        final String[] applications = annotation.value();
        return appsInstalled(applications);
    }


    private boolean appsInstalled(final String... applications) {
        for (final String application : applications) {
            final String app = PropUtils.injectProperties(application);
            if (!isAppInstalled(app)) {
                return false;
            }
        }
        return applications.length > 0;
    }

    private boolean isAppInstalled(final String application) {
        try {
            return Runtime.getRuntime().exec(application) != null;
        } catch (final Exception ex) {
            return false;
        }
    }

}
