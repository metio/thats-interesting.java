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
