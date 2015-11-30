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

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isSatisfied(final CheckerContext<ExistsOnFS> context) throws Exception {
        final ExistsOnFS annotation = context.getAnnotation();
        final ExistsOnFS.Type[] types = annotation.type();
        final String[] filePaths = annotation.value();

        return isSatisfied(filePaths, types);
    }


    private boolean isSatisfied(
        final String[] filePaths, final ExistsOnFS.Type... types
    ) throws Exception {
        for (final String filePath : filePaths) {
            if (isSatisfied(filePath, types)) {
                return false;
            }
        }
        return filePaths.length > 0;
    }

    private boolean isSatisfied(
        final String filePath, final ExistsOnFS.Type... types
    ) throws Exception {
        for (final ExistsOnFS.Type type : types) {
            if (!existsOnFS(filePath, type)) {
                return true;
            }
        }
        return false;
    }

    private boolean existsOnFS(
        final String filePath, final ExistsOnFS.Type type
    ) throws Exception {
        final String path = PropUtils.injectProperties(filePath);
        switch (type) {
            case FILE:
                return FSUtils.fileExists(path);
            case DIRECTORY:
                return FSUtils.directoryExists(path);
            case SYMLINK:
                return FSUtils.exists(path) && FSUtils.isSymlink(path);
            default:
                throw new UnsupportedOperationException("Unsupported type of FS element");
        }
    }

}
