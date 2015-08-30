package com.github.vbauer.jconditions.checker;

import com.github.vbauer.jconditions.annotation.ExistsOnFS;
import com.github.vbauer.jconditions.core.CheckerContext;
import com.github.vbauer.jconditions.core.ConditionChecker;
import com.github.vbauer.jconditions.util.FSUtils;
import com.github.vbauer.jconditions.util.PropUtils;

/**
 * @author Vladislav Bauer
 */

public class ExistsOnFSChecker implements ConditionChecker<ExistsOnFS> {

    @Override
    public boolean isSatisfied(final CheckerContext<ExistsOnFS> context) throws Exception {
        final ExistsOnFS annotation = context.getAnnotation();
        final ExistsOnFS.Type type = annotation.type();
        final String[] filePaths = annotation.value();

        for (final String filePath : filePaths) {
            if (!existsOnFS(filePath, type)) {
                return false;
            }
        }
        return true;
    }


    private boolean existsOnFS(final String filePath, final ExistsOnFS.Type type) {
        final String path = PropUtils.injectProperties(filePath);
        switch (type) {
            case FILE:
                return FSUtils.fileExists(path);
            case DIRECTORY:
                return FSUtils.directoryExists(path);
            default:
                throw new UnsupportedOperationException("Unsupported type of FS element");
        }
    }

}
