/*
 * This file is part of TI. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of TI,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */

package wtf.metio.ti.converter;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.function.Supplier;

/**
 * A converter for method invocations.
 *
 * @param <OUTPUT> The result type of the method invocation.
 */
@FunctionalInterface
public interface InvocationConverter<OUTPUT> {

    /**
     * @param proxy  The proxied logger interface.
     * @param method The invoked method.
     * @param args   The used method parameters.
     * @param extras The extra values to use.
     * @return The computed result.
     */
    OUTPUT convert(
            Object proxy,
            Method method,
            Object[] args,
            Map<String, Supplier<Object>> extras);

}
