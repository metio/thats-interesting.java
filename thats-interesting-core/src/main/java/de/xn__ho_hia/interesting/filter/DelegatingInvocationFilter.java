package de.xn__ho_hia.interesting.filter;

import java.lang.reflect.Method;
import java.util.List;

/**
 * Filter that delegates to other filters.
 */
public class DelegatingInvocationFilter implements InvocationFilter {

    private final List<InvocationFilter> filters;

    /**
     * @param filters
     *            The filters to use.
     */
    public DelegatingInvocationFilter(final List<InvocationFilter> filters) {
        this.filters = filters;
    }

    @Override
    public boolean accept(final Object proxy, final Method method, final Object[] args) {
        return filters.stream().allMatch(filter -> filter.accept(proxy, method, args));
    }

}
