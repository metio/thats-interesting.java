package wtf.metio.ti.converter.xstream.internal;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.function.Supplier;

/**
 *
 */
public final class MethodInvocationModel {

    private final Method method;
    private final Map<String, Object> parameterNamesAndValues;
    private final Map<String, Supplier<Object>> extras;

    /**
     * @param method                  The invoked method.
     * @param parameterNamesAndValues The parameter names and their associated values.
     * @param extras                  The extra values to use.
     */
    public MethodInvocationModel(
            final Method method,
            final Map<String, Object> parameterNamesAndValues,
            final Map<String, Supplier<Object>> extras) {
        this.method = method;
        this.parameterNamesAndValues = parameterNamesAndValues;
        this.extras = extras;
    }

    /**
     * @return The invoked method.
     */
    public Method getMethod() {
        return method;
    }

    /**
     * @return The parameters names and their associated values.
     */
    public Map<String, Object> getParameterNamesAndValues() {
        return parameterNamesAndValues;
    }

    /**
     * @return the extras
     */
    public Map<String, Supplier<Object>> getExtras() {
        return extras;
    }
}
