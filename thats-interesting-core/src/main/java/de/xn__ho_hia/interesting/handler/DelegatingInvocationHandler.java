package de.xn__ho_hia.interesting.handler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Delegating invocation handler that calls all given handlers in order they were given.
 */
public final class DelegatingInvocationHandler extends AbstractNullReturningInvocationHandler {

    private final InvocationHandler[] handlers;

    DelegatingInvocationHandler(final InvocationHandler[] handlers) {
        this.handlers = handlers;
    }

    /**
     * @param handlers
     *            The handlers to call.
     */
    @SuppressWarnings("null")
    public DelegatingInvocationHandler(final List<InvocationHandler> handlers) {
        this(handlers.toArray(new InvocationHandler[handlers.size()]));
    }

    @Override
    protected void invokeHandler(final Object proxy, final Method method, final Object[] args) throws Throwable {
        for (final InvocationHandler handler : handlers) {
            handler.invoke(proxy, method, args);
        }
    }

}
