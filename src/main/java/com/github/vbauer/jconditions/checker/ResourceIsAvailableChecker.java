package com.github.vbauer.jconditions.checker;

import com.github.vbauer.jconditions.annotation.ResourceIsAvailable;
import com.github.vbauer.jconditions.core.CheckerContext;
import com.github.vbauer.jconditions.core.ConditionChecker;
import com.github.vbauer.jconditions.util.FSUtils;
import com.github.vbauer.jconditions.util.InOutUtils;
import com.github.vbauer.jconditions.util.PropUtils;

import java.io.File;
import java.net.URLConnection;

/**
 * @author Vladislav Bauer
 */

public class ResourceIsAvailableChecker implements ConditionChecker<ResourceIsAvailable> {

    @Override
    public boolean isSatisfied(
        final CheckerContext<ResourceIsAvailable> context
    ) throws Exception {
        final ResourceIsAvailable annotation = context.getAnnotation();
        final String source = PropUtils.injectProperties(annotation.source());
        final String target = PropUtils.injectProperties(annotation.target());
        final int timeout = annotation.timeout();
        final boolean cache = annotation.cache();

        if (!FSUtils.fileExists(target) || (!cache && FSUtils.deleteFile(target))) {
            final URLConnection urlConnection = InOutUtils.connectURL(source, timeout);
            final File file = InOutUtils.copyURLContentToFile(urlConnection, target);
            if (!cache) {
                file.deleteOnExit();
            }
            return FSUtils.fileExists(target);
        }
        return true;
    }

}
