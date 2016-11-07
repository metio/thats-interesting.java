package de.xn__ho_hia.interesting.handler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

abstract class NullReturningInvocationHandler implements InvocationHandler {

    @Override
    public final Object invoke(final Object proxy, final Method method, final Object[] args) throws Throwable {
        invokeHandler(proxy, method, args);
        return null;
    }

    protected abstract void invokeHandler(Object proxy, Method method, Object[] args) throws Throwable;

}
