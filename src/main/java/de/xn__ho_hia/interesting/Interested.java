package de.xn__ho_hia.interesting;

import de.xn__ho_hia.interesting.dsl.LoggerBuilder;

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
                .invocationHandler(new SystemOutInvocationHandler());
    }

}
