package de.xn__ho_hia.interesting;

import static de.xn__ho_hia.interesting.handler.StandardInvocationHandlers.delegate;
import static de.xn__ho_hia.interesting.handler.StandardInvocationHandlers.logFile;
import static de.xn__ho_hia.interesting.handler.StandardInvocationHandlers.systemOut;

import java.nio.file.Paths;

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
    @SuppressWarnings({ "nls", "null" })
    public static final <LOGGER> LOGGER in(final Class<LOGGER> logger) {
        return new LoggerBuilder<>(logger)
                .invocationHandler(delegate(
                        systemOut(),
                        logFile(Paths.get("target/file.log")),
                        logFile(Paths.get("target/another.log"))))
                .createLogger();
    }

}
