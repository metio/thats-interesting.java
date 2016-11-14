package de.xn__ho_hia.interesting;

import static de.xn__ho_hia.interesting.handler.StandardInvocationHandlers.delegate;
import static de.xn__ho_hia.interesting.handler.StandardInvocationHandlers.logFile;

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
    @SuppressWarnings("nls")
    public static final <LOGGER> LOGGER in(final Class<LOGGER> logger) {
        return new LoggerBuilder<>(logger)
                .invocationHandler(delegate(
                        logFile("target/triple.log"),
                        logFile("target/triple.log"),
                        logFile("target/triple.log"),
                        logFile("target/another.log")))
                .createLogger();
    }

}
