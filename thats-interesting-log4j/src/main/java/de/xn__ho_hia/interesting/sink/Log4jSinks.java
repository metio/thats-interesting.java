package de.xn__ho_hia.interesting.sink;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Factory for Log4j sinks.
 */
public class Log4jSinks {

    /**
     * @return A consumer that uses java.util.logging
     */
    public static final Consumer<String> log4j() {
        return log4j(Log4jSinks.class);
    }

    /**
     * @param loggerClass
     *            The class of the logger which writes into the sink.
     * @return A consumer that uses Log4j
     */
    public static final Consumer<String> log4j(final Class<?> loggerClass) {
        return log4j(loggerClass, (logger, message) -> logger.info(message));
    }

    /**
     * @param sink
     *            The sink to use.
     * @return A consumer that uses java.util.logging
     */
    public static final Consumer<String> log4j(final BiConsumer<Logger, String> sink) {
        return log4j(Log4jSinks.class, sink);
    }

    /**
     * @param loggerClass
     *            The class of the logger which writes into the sink.
     * @param sink
     *            The sink to use.
     * @return A consumer that uses java.util.logging
     */
    public static final Consumer<String> log4j(final Class<?> loggerClass, final BiConsumer<Logger, String> sink) {
        return log4jSink(LogManager.getLogger(loggerClass), sink);
    }

    /**
     * @param logger
     *            The logger to use.
     * @param sink
     *            The sink to use.
     * @return A consumer that uses java.util.logging
     */
    public static final Consumer<String> log4jSink(final Logger logger, final BiConsumer<Logger, String> sink) {
        return message -> sink.accept(logger, message);
    }

}
