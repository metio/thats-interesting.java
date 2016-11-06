package de.xn__ho_hia.interesting;

import static de.xn__ho_hia.interesting.handler.StandardInvocationHandlers.delegate;
import static de.xn__ho_hia.interesting.handler.StandardInvocationHandlers.javaUtilLogging;
import static de.xn__ho_hia.interesting.handler.StandardInvocationHandlers.javaUtilLoggingWarning;
import static de.xn__ho_hia.interesting.handler.StandardInvocationHandlers.systemOut;

/**
 *
 *
 */
public class Interested {

    /**
     * @param logger
     *            The logger interface to proxy.
     * @return A logger builder for the given interface.
     */
    public static final <LOGGER> LoggerBuilder<LOGGER> in(final Class<LOGGER> logger) {
        return new LoggerBuilder<>(logger)
                .invocationHandler(delegate(
                        systemOut(),
                        systemOut(),
                        javaUtilLogging(),
                        javaUtilLogging(),
                        javaUtilLoggingWarning()));
    }

}
