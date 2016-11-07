package de.xn__ho_hia.interesting;

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
    public static final <LOGGER> LOGGER in(final Class<LOGGER> logger) {
        return new LoggerBuilder<>(logger)
                .invocationHandler(systemOut())
                .createLogger();
    }

}
