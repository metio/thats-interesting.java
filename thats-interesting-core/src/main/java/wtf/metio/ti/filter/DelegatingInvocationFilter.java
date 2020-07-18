/*
 * This file is part of TI. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of TI,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */

package wtf.metio.ti.filter;

import java.lang.reflect.Method;
import java.util.Collection;

/**
 * Filter that delegates to other filters.
 */
public final class DelegatingInvocationFilter implements InvocationFilter {

    private final Collection<InvocationFilter> filters;

    /**
     * @param filters The filters to use.
     */
    public DelegatingInvocationFilter(final Collection<InvocationFilter> filters) {
        this.filters = filters;
    }

    @Override
    public boolean accept(final Object proxy, final Method method, final Object[] args) {
        return filters.stream().allMatch(filter -> filter.accept(proxy, method, args));
    }

}
