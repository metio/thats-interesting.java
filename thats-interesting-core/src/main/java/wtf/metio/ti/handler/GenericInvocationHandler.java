/*
 * This file is part of TI. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of TI,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */

package wtf.metio.ti.handler;

import wtf.metio.ti.converter.InvocationConverter;
import wtf.metio.ti.filter.InvocationFilter;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;

import static java.util.Objects.requireNonNull;

/**
 * Generic invocation handler that calls a converter function and a consumer.
 *
 * @param <OUTPUT> The type of the logger sink.
 */
public final class GenericInvocationHandler<OUTPUT> extends AbstractNullReturningInvocationHandler {

    private final InvocationFilter filter;
    private final InvocationConverter<OUTPUT> converter;
    private final Consumer<OUTPUT> sink;
    private final Map<String, Supplier<Object>> extras;

    /**
     * @param filter    The filter to use.
     * @param converter The converter to use.
     * @param sink      The sink to use.
     * @param extras    The extra values to use.
     */
    public GenericInvocationHandler(
            final InvocationFilter filter,
            final InvocationConverter<OUTPUT> converter,
            final Consumer<OUTPUT> sink,
            final Map<String, Supplier<Object>> extras) {
        this.filter = filter;
        this.converter = converter;
        this.sink = sink;
        this.extras = requireNonNull(Collections.unmodifiableMap(extras));
    }

    @Override
    protected void invokeHandler(final Object proxy, final Method method, final Object[] args) throws Throwable {
        if (filter.accept(proxy, method, args)) {
            sink.accept(converter.convert(proxy, method, args, extras));
        }
    }

}
