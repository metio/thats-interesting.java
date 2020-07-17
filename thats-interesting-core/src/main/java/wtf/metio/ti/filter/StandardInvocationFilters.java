package wtf.metio.ti.filter;

import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;

/**
 *
 */
public final class StandardInvocationFilters {

    /**
     * @param allowedMethodNames The names of methods allowed to pass the newly created filter.
     * @return A new invocation filter that filters invocations based on method names.
     */
    public static InvocationFilter methods(final String... allowedMethodNames) {
        return new MethodNameInvocationFilter(arrayAsList(allowedMethodNames));
    }

    /**
     * @param filters The filtes to delegate to.
     * @return A new invocation filter that filters invocations based on method names.
     */
    public static InvocationFilter delegate(final InvocationFilter... filters) {
        return delegate(arrayAsList(filters));
    }

    /**
     * @param filters The filtes to delegate to.
     * @return A new invocation filter that filters invocations based on method names.
     */
    public static InvocationFilter delegate(final Collection<InvocationFilter> filters) {
        return new DelegatingInvocationFilter(filters);
    }

    private static <TYPE> Collection<TYPE> arrayAsList(final TYPE[] values) {
        return Objects.requireNonNull(Arrays.asList(values));
    }

    private StandardInvocationFilters() {
        // factory class
    }

}
