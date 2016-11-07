package de.xn__ho_hia.interesting.sink;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.logging.Logger;

/**
 * Factory for java.util.logging based sinks.
 */
public class JULSinks {

    /**
     * @return A consumer that uses java.util.logging
     */
    public static final Consumer<String> globalLogger() {
        return globalLogger((logger, message) -> logger.info(message));
    }

    /**
     * @param sink
     *            The sink to use.
     * @return A consumer that uses java.util.logging
     */
    public static final Consumer<String> globalLogger(final BiConsumer<Logger, String> sink) {
        return logger(Logger.getGlobal(), sink);
    }

    /**
     * @param logger
     *            The logger to use.
     * @param sink
     *            The sink to use.
     * @return A consumer that uses java.util.logging
     */
    public static final Consumer<String> logger(final Logger logger, final BiConsumer<Logger, String> sink) {
        return msg -> sink.accept(logger, msg);
    }

}
