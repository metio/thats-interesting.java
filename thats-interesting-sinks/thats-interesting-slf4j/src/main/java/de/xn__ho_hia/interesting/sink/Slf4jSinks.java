package de.xn__ho_hia.interesting.sink;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Factory for Slf4j sinks.
 */
public class Slf4jSinks {

    /**
     * @return A consumer that uses Slf4j
     */
    public static final Consumer<String> slf4j() {
        return slf4j(Slf4jSinks.class);
    }

    /**
     * @param loggerClass
     *            The class of the logger which writes into the sink.
     * @return A consumer that uses Slf4j
     */
    public static final Consumer<String> slf4j(final Class<?> loggerClass) {
        return slf4j(loggerClass, (logger, message) -> logger.info(message));
    }

    /**
     * @param sink
     *            The sink to use.
     * @return A consumer that uses Slf4j
     */
    public static final Consumer<String> slf4j(final BiConsumer<Logger, String> sink) {
        return slf4j(Slf4jSinks.class, sink);
    }

    /**
     * @param loggerClass
     *            The class of the logger which writes into the sink.
     * @param sink
     *            The sink to use.
     * @return A consumer that uses Slf4j
     */
    @SuppressWarnings("null")
    public static final Consumer<String> slf4j(final Class<?> loggerClass, final BiConsumer<Logger, String> sink) {
        return slf4jSink(LoggerFactory.getLogger(loggerClass), sink);
    }

    /**
     * @param logger
     *            The logger to use.
     * @param sink
     *            The sink to use.
     * @return A consumer that uses Slf4j
     */
    public static final Consumer<String> slf4jSink(final Logger logger, final BiConsumer<Logger, String> sink) {
        return message -> sink.accept(logger, message);
    }

}
