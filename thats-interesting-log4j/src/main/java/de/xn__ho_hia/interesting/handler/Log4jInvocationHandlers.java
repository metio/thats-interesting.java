package de.xn__ho_hia.interesting.handler;

import static de.xn__ho_hia.interesting.handler.StandardInvocationHandlers.stringFormat;

import java.lang.reflect.InvocationHandler;
import java.util.function.BiConsumer;

import org.apache.logging.log4j.Logger;

import de.xn__ho_hia.interesting.sink.Log4jSinks;

/**
 * Factory for Log4j based {@link InvocationHandler}s.
 */
public final class Log4jInvocationHandlers {

    /**
     * @return An invocation handler that prints method invocations to Log4j
     */
    public static final InvocationHandler log4jInfo() {
        return log4j((logger, message) -> logger.info(message));
    }

    /**
     * @return An invocation handler that prints method invocations to Log4j
     */
    public static final InvocationHandler log4jWarning() {
        return log4j((logger, message) -> logger.warn(message));
    }

    /**
     * @return An invocation handler that prints method invocations to Log4j
     */
    public static final InvocationHandler log4jDebug() {
        return log4j((logger, message) -> logger.debug(message));
    }

    /**
     * @return An invocation handler that prints method invocations to Log4j
     */
    public static final InvocationHandler log4jError() {
        return log4j((logger, message) -> logger.error(message));
    }

    /**
     * @return An invocation handler that prints method invocations to Log4j
     */
    public static final InvocationHandler log4jTraceEntry() {
        return log4j((logger, message) -> logger.traceEntry(message));
    }

    /**
     * @return An invocation handler that prints method invocations to Log4j
     */
    public static final InvocationHandler log4jTraceExit() {
        return log4j((logger, message) -> logger.traceExit(message));
    }

    /**
     * @return An invocation handler that prints method invocations to Log4j
     */
    public static final InvocationHandler log4jTrace() {
        return log4j((logger, message) -> logger.trace(message));
    }

    /**
     * @return An invocation handler that prints method invocations to Log4j
     */
    public static final InvocationHandler log4jFatal() {
        return log4j((logger, message) -> logger.fatal(message));
    }

    /**
     * @param sink
     *            The sink to use.
     * @return An invocation handler that prints method invocations to java.util.logging
     */
    public static final InvocationHandler log4j(final BiConsumer<Logger, String> sink) {
        return stringFormat(Log4jSinks.log4j(sink));
    }

}
