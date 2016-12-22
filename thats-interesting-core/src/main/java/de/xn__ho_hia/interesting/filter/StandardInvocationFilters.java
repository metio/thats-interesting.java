package de.xn__ho_hia.interesting.filter;

import java.util.Arrays;
import java.util.List;

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

    /**
     * @param filters
     *            The filtes to delegate to.
     * @return A new invocation filter that filters invocations based on method names.
     */
    @SuppressWarnings("null")
    public static InvocationFilter delegate(final InvocationFilter... filters) {
        return delegate(Arrays.asList(filters));
    }

    /**
     * @param filters
     *            The filtes to delegate to.
     * @return A new invocation filter that filters invocations based on method names.
     */
    public static InvocationFilter delegate(final List<InvocationFilter> filters) {
        return new DelegatingInvocationFilter(filters);
    }

    private StandardInvocationFilters() {
        // factory class
    }

}
