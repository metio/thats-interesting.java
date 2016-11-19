package de.xn__ho_hia.interesting.builder;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

import de.xn__ho_hia.interesting.converter.InvocationConverter;
import de.xn__ho_hia.interesting.filter.InvocationFilter;
import de.xn__ho_hia.interesting.handler.DelegatingInvocationHandler;
import de.xn__ho_hia.interesting.handler.GenericInvocationHandler;

/**
 * @param <LOGGER>
 *            The logger interface to proxy.
 */
public final class LoggerBuilder<LOGGER> {

    private final Class<LOGGER>           logger;
    private final List<InvocationHandler> handlers;

    /**
     * @param logger
     *            The logger interface to proxy.
     * @param handlers
     *            The handlers to invoke.
     */
    public LoggerBuilder(final Class<LOGGER> logger, final List<InvocationHandler> handlers) {
        this.logger = logger;
        this.handlers = handlers;
    }

    /**
     * @param newHandler
     *            The method invocation handler to use.
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
                new Class<?>[] { logger },
                new DelegatingInvocationHandler(handlers));
    }

    /**
     * @param <LOGGER>
     *            The logger interface to proxy.
     */
    public static final class InvocationHandlerBuilder<LOGGER> {

        private final LoggerBuilder<LOGGER>  loggerBuilder;

        private final List<InvocationFilter> filters = new ArrayList<>();
        private InvocationConverter<String>  converter;

        /**
         * @param loggerBuilder
         *            The logger builder to use.
         */
        @SuppressWarnings("null")
        public InvocationHandlerBuilder(final LoggerBuilder<LOGGER> loggerBuilder) {
            this.loggerBuilder = loggerBuilder;
        }

        /**
         * @param newFilters
         *            The filters to add.
         * @return The current builder reconfigured with the additional filters.
         */
        @SuppressWarnings("null")
        public InvocationHandlerBuilder<LOGGER> filters(final InvocationFilter... newFilters) {
            this.filters.addAll(Arrays.asList(newFilters));
            return this;
        }

        /**
         * @param newConverter
         *            The converter to use.
         * @return The current builder reconfigured with the given converter.
         */
        public InvocationHandlerBuilder<LOGGER> converter(
                final InvocationConverter<String> newConverter) {
            this.converter = newConverter;
            return this;
        }

        /**
         * @param sink
         *            The sinks to use. Has to be more than one.
         * @return A newly created logger builder using the given sinks.
         */
        public LoggerBuilder<LOGGER> sinks(final Consumer<String> sink) {
            return loggerBuilder.invocationHandler(new GenericInvocationHandler<>(filters, converter, sink));
        }

    }

}
