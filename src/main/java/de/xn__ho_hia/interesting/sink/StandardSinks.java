package de.xn__ho_hia.interesting.sink;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.logging.Logger;

/**
 * Factory for standard sinks.
 */
public class StandardSinks {

    /**
     * @return A consumer that uses System.out
     */
    public static final Consumer<String> systemOut() {
        return System.out::println;
    }

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
        final Logger logger = Logger.getGlobal();
        final Consumer<String> output = msg -> sink.accept(logger, msg);
        return output;
    }

}
