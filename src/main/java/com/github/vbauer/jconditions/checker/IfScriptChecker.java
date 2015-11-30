package com.github.vbauer.jconditions.checker;

import java.util.concurrent.Callable;

import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.SimpleScriptContext;

import com.github.vbauer.jconditions.annotation.IfScript;
import com.github.vbauer.jconditions.core.CheckerContext;
import com.github.vbauer.jconditions.core.ConditionChecker;
import com.github.vbauer.jconditions.util.PropUtils;
import com.github.vbauer.jconditions.util.ReflexUtils;
import com.github.vbauer.jconditions.util.ScriptUtils;

/**
 * @author Vladislav Bauer
 */

public class IfScriptChecker implements ConditionChecker<IfScript> {

    public static final String CONTEXT_ENV = "env";
    public static final String CONTEXT_PROPS = "props";
    public static final String CONTEXT_TEST = "test";
    public static final String CONTEXT_CONSOLE = "console";
    public static final String CONTEXT_CONTEXT = "context";


    @Override
    public boolean isSatisfied(final CheckerContext<IfScript> context) throws Exception {
        final Object testInstance = context.getInstance();
        final IfScript annotation = context.getAnnotation();

		@SuppressWarnings("rawtypes")
		final Class<? extends Callable> contextProviderClass = annotation.context();
        final String engineName = annotation.engine();
        final String[] scripts = annotation.value();

        final ScriptEngine scriptEngine = ScriptUtils.findScriptEngine(engineName);
        if (scriptEngine != null) {
            final Object extraContext = getExtraContext(testInstance, contextProviderClass);
            final ScriptContext scriptContext = createScriptContext(testInstance, extraContext);
            return isSatisfied(scriptEngine, scriptContext, scripts);
        }
        return false;
    }


    private boolean isSatisfied(
        final ScriptEngine scriptEngine, final ScriptContext scriptContext, final String... scripts
    ) throws Exception {
        for (final String script : scripts) {
            final Object eval = scriptEngine.eval(script, scriptContext);
            if (!isTrueValue(eval)) {
                return false;
            }
        }
        return scripts.length > 0;
    }

    private boolean isTrueValue(final Object value) {
        if (value instanceof Number) {
            return ((Number) value).doubleValue() > 0;
        }
        return Boolean.valueOf(String.valueOf(value));
    }

    private ScriptContext createScriptContext(
        final Object testInstance, final Object extraContext
    ) {
        final ScriptContext context = new SimpleScriptContext();
        ScriptUtils.addAttribute(context, CONTEXT_CONTEXT, extraContext);
        ScriptUtils.addAttribute(context, CONTEXT_TEST, testInstance);
        ScriptUtils.addAttribute(context, CONTEXT_CONSOLE, System.console());
        ScriptUtils.addAttribute(context, CONTEXT_ENV, System.getenv());
        ScriptUtils.addAttribute(
            context, CONTEXT_PROPS,
            PropUtils.convertPropertiesToMap(System.getProperties())
        );
        return context;
    }

    @SuppressWarnings("rawtypes")
	private Object getExtraContext(
        final Object testInstance, final Class<? extends Callable> providerClass
    ) throws Exception {
        if (providerClass != null && providerClass != Callable.class) {
            final Callable<?> provider = ReflexUtils.instantiate(testInstance, providerClass);
            return provider.call();
        }
        return null;
    }

}
