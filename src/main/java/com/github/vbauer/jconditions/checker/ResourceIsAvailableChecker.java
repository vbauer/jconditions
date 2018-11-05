package com.github.vbauer.jconditions.checker;

import com.github.vbauer.jconditions.annotation.ResourceIsAvailable;
import com.github.vbauer.jconditions.core.CheckerContext;
import com.github.vbauer.jconditions.core.ConditionChecker;
import com.github.vbauer.jconditions.util.FSUtils;
import com.github.vbauer.jconditions.util.NetUtils;
import com.github.vbauer.jconditions.util.PropUtils;

import java.io.File;
import java.net.URLConnection;

/**
 * @author Vladislav Bauer
 */

public class ResourceIsAvailableChecker implements ConditionChecker<ResourceIsAvailable> {

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isSatisfied(final CheckerContext<ResourceIsAvailable> context) throws Exception {
        final ResourceIsAvailable annotation = context.getAnnotation();
        final String source = NetUtils.fixScheme(PropUtils.injectProperties(annotation.source()));
        final String target = PropUtils.injectProperties(annotation.target());
        final int timeout = annotation.timeout();
        final boolean cache = annotation.cache();

        if (!FSUtils.fileExists(target) || !cache && FSUtils.deleteFile(target)) {
            final URLConnection urlConnection = NetUtils.connectURL(source, timeout);
            final File file = NetUtils.copyURLContentToFile(urlConnection, target);
            if (!cache) {
                file.deleteOnExit();
            }
            return FSUtils.fileExists(target);
        }
        return true;
    }

}
