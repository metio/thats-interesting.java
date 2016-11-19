package de.xn__ho_hia.interesting.handler;

import java.lang.reflect.Method;
import java.util.function.Consumer;

import de.xn__ho_hia.interesting.converter.InvocationConverter;
import de.xn__ho_hia.interesting.filter.InvocationFilter;

/**
 * Generic invocation handler that calls a converter function and a consumer.
 *
 * @param <OUTPUT>
 *            The type of the logger sink.
 */
public final class GenericInvocationHandler<OUTPUT> extends AbstractNullReturningInvocationHandler {

    private final InvocationFilter            filter;
    private final InvocationConverter<OUTPUT> converter;
    private final Consumer<OUTPUT>            sink;

    /**
     * @param filter
     *            The filter to use.
     * @param converter
     *            The converter to use.
     * @param sink
     *            The sink to use.
     */
    public GenericInvocationHandler(
            final InvocationFilter filter,
            final InvocationConverter<OUTPUT> converter,
            final Consumer<OUTPUT> sink) {
        this.filter = filter;
        this.converter = converter;
        this.sink = sink;
    }

    @Override
    protected void invokeHandler(final Object proxy, final Method method, final Object[] args) throws Throwable {
        if (filter.accept(proxy, method, args)) {
            sink.accept(converter.convert(proxy, method, args));
        }
    }

}
