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
 * Filters invocations based on the method name of the called logging interface.
 */
public final class MethodNameInvocationFilter implements InvocationFilter {

    private final Collection<String> allowedMethodNames;

    /**
     * @param allowedMethodNames The names of methods that are allowed to pass this filter.
     */
    public MethodNameInvocationFilter(final Collection<String> allowedMethodNames) {
        this.allowedMethodNames = allowedMethodNames;
    }

    @Override
    public boolean accept(final Object proxy, final Method method, final Object[] args) {
        return allowedMethodNames.contains(method.getName());
    }

}
