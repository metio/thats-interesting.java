/*
 * This file is part of TI. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of TI,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */

package wtf.metio.ti.handler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import static java.util.Objects.requireNonNull;

abstract class AbstractNullReturningInvocationHandler implements InvocationHandler {

    @Override
    public final Object invoke(
            final Object proxy,
            final Method method,
            final Object[] args) throws Throwable {
        invokeHandler(requireNonNull(proxy), requireNonNull(method), requireNonNull(args));
        return null;
    }

    protected abstract void invokeHandler(Object proxy, Method method, Object[] args) throws Throwable;

}
