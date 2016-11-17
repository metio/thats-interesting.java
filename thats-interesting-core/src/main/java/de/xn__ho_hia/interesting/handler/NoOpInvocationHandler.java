package de.xn__ho_hia.interesting.handler;

import java.lang.reflect.Method;

/**
 * Invocation handler that does nothing.
 */
public final class NoOpInvocationHandler extends NullReturningInvocationHandler {

    @Override
    protected void invokeHandler(final Object proxy, final Method method, final Object[] args) throws Throwable {
        // do nothing
    }

}
