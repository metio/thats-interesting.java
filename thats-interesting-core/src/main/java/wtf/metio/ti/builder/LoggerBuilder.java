package wtf.metio.ti.builder;

import wtf.metio.ti.converter.InvocationConverter;
import wtf.metio.ti.filter.InvocationFilter;
import wtf.metio.ti.filter.StandardInvocationFilters;
import wtf.metio.ti.handler.StandardInvocationHandlers;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * @param <LOGGER> The logger interface to proxy.
 */
public final class LoggerBuilder<LOGGER> {

    private final Class<LOGGER> logger;
    private final List<InvocationHandler> handlers;
    private final Map<String, Supplier<Object>> extras;

    /**
     * @param logger   The logger interface to proxy.
     * @param handlers The handlers to invoke.
     * @param extras   The static extras to use.
     */
    public LoggerBuilder(
            final Class<LOGGER> logger,
            final List<InvocationHandler> handlers,
            final Map<String, Supplier<Object>> extras) {
        this.logger = logger;
        this.handlers = handlers;
        this.extras = extras;
    }

    /**
     * @param keyName The name of the static extra.
     * @param value   The constant value of the extra.
     * @return The current builder reconfigured with the given extra static value.
     */
    public LoggerBuilder<LOGGER> withStaticExtra(final String keyName, final Object value) {
        return withSuppliedExtra(keyName, () -> value);
    }

    /**
     * @param keyName  The name of the static extra.
     * @param supplier The supplier for the extra value.
     * @return The current builder reconfigured with the given extra supplied value.
     */
    public LoggerBuilder<LOGGER> withSuppliedExtra(final String keyName,
                                                   final Supplier<Object> supplier) {
        extras.put(keyName, supplier);
        return this;
    }

    /**
     * @param newHandler The method invocation handler to use.
     * @return A logger builder configured to use the given handler.
     */
    public LoggerBuilder<LOGGER> invocationHandler(final InvocationHandler newHandler) {
        handlers.add(newHandler);
        return this;
    }

    /**
     * @return A newly created invocation handler builder.
     */
    public InvocationHandlerBuilder<LOGGER> buildHandler() {
        return new InvocationHandlerBuilder<>(this);
    }

    /**
     * @return A logging proxy for the configured logger interface.
     */
    @SuppressWarnings("unchecked")
    public LOGGER createLogger() {
        return (LOGGER) Proxy.newProxyInstance(
                logger.getClassLoader(),
                new Class<?>[]{logger},
                StandardInvocationHandlers.delegate(handlers));
    }

    Map<String, Supplier<Object>> getCopyOfExtras() {
        return new LinkedHashMap<>(extras);
    }

    /**
     * @param <LOGGER> The logger interface to proxy.
     */
    public static final class InvocationHandlerBuilder<LOGGER> {

        private final LoggerBuilder<LOGGER> loggerBuilder;

        private final List<InvocationFilter> filters = new ArrayList<>();
        private InvocationConverter<String> converter;
        private final Map<String, Supplier<Object>> extras;

        /**
         * @param loggerBuilder The logger builder to use.
         */
        @SuppressWarnings("null")
        public InvocationHandlerBuilder(final LoggerBuilder<LOGGER> loggerBuilder) {
            this.loggerBuilder = loggerBuilder;
            extras = loggerBuilder.getCopyOfExtras();
        }

        /**
         * @param newFilters The filters to add.
         * @return The current builder reconfigured with the additional filters.
         */
        @SuppressWarnings("null")
        public InvocationHandlerBuilder<LOGGER> filters(final InvocationFilter... newFilters) {
            this.filters.addAll(Arrays.asList(newFilters));
            return this;
        }

        /**
         * @param newConverter The converter to use.
         * @return The current builder reconfigured with the given converter.
         */
        public InvocationHandlerBuilder<LOGGER> converter(
                final InvocationConverter<String> newConverter) {
            this.converter = newConverter;
            return this;
        }

        /**
         * @param keyName The name of the static extra.
         * @param value   The constant value of the extra.
         * @return The current builder reconfigured with the given extra static value.
         */
        public InvocationHandlerBuilder<LOGGER> withStaticExtra(final String keyName, final Object value) {
            return withSuppliedExtra(keyName, () -> value);
        }

        /**
         * @param keyName  The name of the static extra.
         * @param supplier The supplier for the extra value.
         * @return The current builder reconfigured with the given extra supplied value.
         */
        public InvocationHandlerBuilder<LOGGER> withSuppliedExtra(final String keyName,
                                                                  final Supplier<Object> supplier) {
            extras.put(keyName, supplier);
            return this;
        }

        /**
         * @param sink The sinks to use. Has to be more than one.
         * @return A newly created logger builder using the given sinks.
         */
        public LoggerBuilder<LOGGER> sinks(final Consumer<String> sink) {
            return loggerBuilder.invocationHandler(
                    StandardInvocationHandlers.generic(
                            StandardInvocationFilters.delegate(filters),
                            converter,
                            sink,
                            extras));
        }

    }

}
