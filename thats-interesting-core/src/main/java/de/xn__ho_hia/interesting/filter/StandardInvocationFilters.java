package de.xn__ho_hia.interesting.filter;

import java.util.Arrays;

/**
 *
 *
 */
public final class StandardInvocationFilters {

    /**
     * @param allowedMethodNames
     *            The names of methods allowed to pass the newly created filter.
     * @return A new invocation filter that filters invocations based on method names.
     */
    @SuppressWarnings("null")
    public static InvocationFilter methods(final String... allowedMethodNames) {
        return new MethodNameInvocationFilter(Arrays.asList(allowedMethodNames));
    }

    private StandardInvocationFilters() {
        // factory class
    }

}
