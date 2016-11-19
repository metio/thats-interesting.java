package de.xn__ho_hia.interesting.converter;

import java.lang.reflect.Method;

/**
 * A converter for method invocations.
 *
 * @param <OUTPUT>
 *            The result type of the method invocation.
 */
@FunctionalInterface
public interface InvocationConverter<OUTPUT> {

    /**
     * @param proxy
     *            The proxied logger interface.
     * @param method
     *            The invoked method.
     * @param args
     *            The used method parameters.
     * @return The computed result.
     */
    OUTPUT convert(final Object proxy, final Method method, final Object[] args);

}
