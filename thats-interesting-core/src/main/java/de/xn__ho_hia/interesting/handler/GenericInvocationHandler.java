package de.xn__ho_hia.interesting.handler;

import java.lang.reflect.Method;
import java.util.function.Consumer;

import de.xn__ho_hia.interesting.converter.MethodInvocationConverter;

/**
 * Generic invocation handler that calls a converter function and a consumer.
 *
 * @param <OUTPUT>
 *            The type of the logger sink.
 */
public class GenericInvocationHandler<OUTPUT> extends NullReturningInvocationHandler {

    private final MethodInvocationConverter<OUTPUT> converter;
    private final Consumer<OUTPUT>                  sink;

    /**
     * @param converter
     *            The converter to use.
     * @param sink
     *            The sink to use.
     */
    public GenericInvocationHandler(
            final MethodInvocationConverter<OUTPUT> converter,
            final Consumer<OUTPUT> sink) {
        this.converter = converter;
        this.sink = sink;
    }

    @Override
    protected void invokeHandler(final Object proxy, final Method method, final Object[] args) throws Throwable {
        sink.accept(converter.convert(proxy, method, args));
    }

}
