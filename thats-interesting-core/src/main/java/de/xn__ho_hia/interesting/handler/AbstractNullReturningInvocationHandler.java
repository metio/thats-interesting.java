package de.xn__ho_hia.interesting.handler;

import static org.eclipse.jdt.annotation.Checks.requireNonNull;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import org.eclipse.jdt.annotation.Nullable;

abstract class AbstractNullReturningInvocationHandler implements InvocationHandler {

    @Override
    public final @Nullable Object invoke(final @Nullable Object proxy, final @Nullable Method method,
            final Object @Nullable [] args) throws Throwable {
        invokeHandler(requireNonNull(proxy), requireNonNull(method), requireNonNull(args));
        return null;
    }

    protected abstract void invokeHandler(Object proxy, Method method, Object[] args) throws Throwable;

}
