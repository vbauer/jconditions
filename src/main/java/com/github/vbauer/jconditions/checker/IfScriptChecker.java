package com.github.vbauer.jconditions.checker;

import com.github.vbauer.jconditions.annotation.IfScript;
import com.github.vbauer.jconditions.core.CheckerContext;
import com.github.vbauer.jconditions.core.ConditionChecker;
import com.github.vbauer.jconditions.util.PropUtils;
import com.github.vbauer.jconditions.util.ScriptUtils;

import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.SimpleScriptContext;

/**
 * @author Vladislav Bauer
 */

public class IfScriptChecker implements ConditionChecker<IfScript> {

    public static final String CONTEXT_ENV = "env";
    public static final String CONTEXT_PROPS = "props";
    public static final String CONTEXT_TEST = "test";
    public static final String CONTEXT_CONSOLE = "console";


    @Override
    public boolean isSatisfied(final CheckerContext<IfScript> context) throws Exception {
        final Object testInstance = context.getInstance();
        final IfScript annotation = context.getAnnotation();
        final String[] scripts = annotation.value();
        final String engineName = annotation.engine();

        return isSatisfied(scripts, engineName, testInstance);
    }


    private boolean isSatisfied(
        final String[] scripts, final String engine, final Object testInstance
    ) throws Exception {
        final ScriptEngine scriptEngine = ScriptUtils.findScriptEngine(engine);
        final ScriptContext scriptContext = createScriptContext(testInstance);

        if (scriptEngine != null) {
            for (final String script : scripts) {
                final Object eval = scriptEngine.eval(script, scriptContext);
                if (!isTrueValue(eval)) {
                    return false;
                }
            }
            return scripts.length > 0;
        }
        return false;
    }

    private boolean isTrueValue(final Object value) {
        if (value instanceof Number) {
            return ((Number) value).doubleValue() > 0;
        }
        return Boolean.valueOf(String.valueOf(value));
    }

    private ScriptContext createScriptContext(final Object testInstance) {
        final ScriptContext context = new SimpleScriptContext();
        ScriptUtils.addAttribute(context, CONTEXT_TEST, testInstance);
        ScriptUtils.addAttribute(context, CONTEXT_CONSOLE, System.console());
        ScriptUtils.addAttribute(context, CONTEXT_ENV, System.getenv());
        ScriptUtils.addAttribute(
            context, CONTEXT_PROPS,
            PropUtils.convertPropertiesToMap(System.getProperties())
        );
        return context;
    }

}
