package de.xn__ho_hia.interesting.converter.internal;

import java.util.Map;

/**
 *
 *
 */
public final class MethodInvocationModel {

    private final Map<String, Object> parameterNamesAndValues;

    /**
     * @param parameterNamesAndValues
     *            The parameter names and their associated values.
     */
    public MethodInvocationModel(final Map<String, Object> parameterNamesAndValues) {
        this.parameterNamesAndValues = parameterNamesAndValues;
    }

    /**
     * @return The parameters names and their associated values.
     */
    public Map<String, Object> getParameterNamesAndValues() {
        return parameterNamesAndValues;
    }
}
