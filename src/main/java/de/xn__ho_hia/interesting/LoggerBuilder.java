package de.xn__ho_hia.interesting;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @param <LOGGER>
 *            The logger interface to proxy.
 */
public final class LoggerBuilder<LOGGER> {

    private final Class<LOGGER> logger;

    private InvocationHandler   handler;

    /**
     * @param logger
     *            The logger interface to proxy.
     */
    public LoggerBuilder(final Class<LOGGER> logger) {
        this.logger = logger;
    }

    /**
     * @param newHandler
     *            The method invocation handler to use.
     * @return A logger builder configured to use the given handler.
     */
    public LoggerBuilder<LOGGER> invocationHandler(final InvocationHandler newHandler) {
        this.handler = newHandler;
        return this;
    }

    /**
     * @return A logging proxy for the configured logger interface.
     */
    @SuppressWarnings("unchecked")
    public LOGGER createLogger() {
        return (LOGGER) Proxy.newProxyInstance(
                logger.getClassLoader(),
                new Class<?>[] { logger },
                handler);
    }

}
