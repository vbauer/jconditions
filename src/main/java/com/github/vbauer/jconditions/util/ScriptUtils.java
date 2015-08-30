package com.github.vbauer.jconditions.util;

import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;
import java.util.List;

/**
 * @author Vladislav Bauer
 */

public final class ScriptUtils {

    private ScriptUtils() {
        throw new UnsupportedOperationException();
    }


    public static ScriptEngine findScriptEngine(final String engine) {
        final ScriptEngineManager engineManager = new ScriptEngineManager();
        final List<ScriptEngineFactory> engineFactories = engineManager.getEngineFactories();

        for (final ScriptEngineFactory engineFactory : engineFactories) {
            if (isSameEngine(engineFactory, engine)) {
                return engineFactory.getScriptEngine();
            }
        }
        return null;
    }

    public static void addAttribute(
        final ScriptContext context, final String key, final Object value
    ) {
        context.setAttribute(key, value, ScriptContext.ENGINE_SCOPE);
    }

    private static boolean isSameEngine(final ScriptEngineFactory factory, final String engine) {
        return sameName(factory, engine)
                || sameLanguage(factory, engine)
                || sameNames(factory, engine)
                || sameMimeTypes(factory, engine);
    }

    private static boolean sameMimeTypes(final ScriptEngineFactory factory, final String engine) {
        final List<String> mimeTypes = factory.getMimeTypes();
        if (mimeTypes != null) {
            for (final String mimeType : mimeTypes) {
                if (TextUtils.containsIgnoreCase(mimeType, engine)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean sameNames(final ScriptEngineFactory factory, final String engine) {
        final List<String> names = factory.getNames();
        if (names != null) {
            for (final String name : names) {
                if (TextUtils.containsIgnoreCase(name, engine)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean sameLanguage(final ScriptEngineFactory factory, final String engine) {
        final String languageName = factory.getLanguageName();
        return TextUtils.containsIgnoreCase(languageName, engine);
    }

    private static boolean sameName(final ScriptEngineFactory factory, final String engine) {
        final String engineName = factory.getEngineName();
        return TextUtils.containsIgnoreCase(engineName, engine);
    }

}
