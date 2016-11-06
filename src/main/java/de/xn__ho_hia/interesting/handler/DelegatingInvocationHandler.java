package de.xn__ho_hia.interesting.handler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Delegating invocation handler that calls all given handlers in order they were given.
 */
public class DelegatingInvocationHandler extends NullReturningInvocationHandler {

    private final InvocationHandler[] handlers;

    DelegatingInvocationHandler(final InvocationHandler[] handlers) {
        this.handlers = handlers;
    }

    @Override
    protected void invokeHandler(final Object proxy, final Method method, final Object[] args) throws Throwable {
        for (final InvocationHandler handler : handlers) {
            handler.invoke(proxy, method, args);
        }
    }

}
