package wtf.metio.ti.handler;

import static wtf.metio.ti.handler.StandardInvocationHandlers.stringFormat;

import java.lang.reflect.InvocationHandler;
import java.util.function.BiConsumer;
import java.util.logging.Logger;

import wtf.metio.ti.sink.JULSinks;

/**
 * Factory for java.util.logging based {@link InvocationHandler}s.
 */
public final class JULInvocationHandlers {

    /**
     * @return An invocation handler that prints method invocations to java.util.logging
     */
    public static final InvocationHandler globalLoggerInfo() {
        return globalLogger((logger, message) -> logger.info(message));
    }

    /**
     * @return An invocation handler that prints method invocations to java.util.logging
     */
    public static final InvocationHandler globalLoggerWarning() {
        return globalLogger((logger, message) -> logger.warning(message));
    }

    /**
     * @return An invocation handler that prints method invocations to java.util.logging
     */
    public static final InvocationHandler globalLoggerFine() {
        return globalLogger((logger, message) -> logger.fine(message));
    }

    /**
     * @return An invocation handler that prints method invocations to java.util.logging
     */
    public static final InvocationHandler globalLoggerConfig() {
        return globalLogger((logger, message) -> logger.config(message));
    }

    /**
     * @return An invocation handler that prints method invocations to java.util.logging
     */
    public static final InvocationHandler globalLoggerFiner() {
        return globalLogger((logger, message) -> logger.finer(message));
    }

    /**
     * @return An invocation handler that prints method invocations to java.util.logging
     */
    public static final InvocationHandler globalLoggerFinest() {
        return globalLogger((logger, message) -> logger.finest(message));
    }

    /**
     * @return An invocation handler that prints method invocations to java.util.logging
     */
    public static final InvocationHandler globalLoggerSevere() {
        return globalLogger((logger, message) -> logger.severe(message));
    }

    /**
     * @param sink
     *            The sink to use.
     * @return An invocation handler that prints method invocations to java.util.logging
     */
    public static final InvocationHandler globalLogger(final BiConsumer<Logger, String> sink) {
        return stringFormat(JULSinks.globalLogger(sink));
    }

}
