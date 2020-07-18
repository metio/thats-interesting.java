/*
 * This file is part of TI. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of TI,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */

package wtf.metio.ti.filter;

import java.lang.reflect.Method;

/**
 * A filter for method invocations.
 */
public interface InvocationFilter {

    /**
     * @param proxy
     * @param method
     * @param args
     * @return <code>true</code> if the input is allowed to pass through the filter, <code>false</code> otherwise.
     */
    boolean accept(Object proxy, Method method, Object[] args);

}
