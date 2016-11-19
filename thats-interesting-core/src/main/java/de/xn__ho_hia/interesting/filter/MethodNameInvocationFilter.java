package de.xn__ho_hia.interesting.filter;

import java.lang.reflect.Method;
import java.util.Collection;

/**
 * Filters invocations based on the method name of the called logging interface.
 */
public final class MethodNameInvocationFilter implements InvocationFilter {

    private final Collection<String> allowedMethodNames;

    /**
     * @param allowedMethodNames
     *            The names of methods that are allowed to pass this filter.
     */
    public MethodNameInvocationFilter(final Collection<String> allowedMethodNames) {
        this.allowedMethodNames = allowedMethodNames;
    }

    @Override
    public boolean accept(final Object proxy, final Method method, final Object[] args) {
        return allowedMethodNames.contains(method.getName());
    }

}
