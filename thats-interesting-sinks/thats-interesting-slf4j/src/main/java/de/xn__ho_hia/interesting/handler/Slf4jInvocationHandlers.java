package de.xn__ho_hia.interesting.handler;

import static de.xn__ho_hia.interesting.handler.StandardInvocationHandlers.stringFormat;

import java.lang.reflect.InvocationHandler;
import java.util.function.BiConsumer;

import org.slf4j.Logger;

import de.xn__ho_hia.interesting.sink.Slf4jSinks;

/**
 * Factory for Slf4j based {@link InvocationHandler}s.
 */
public final class Slf4jInvocationHandlers {

    /**
     * @return An invocation handler that prints method invocations to Slf4j
     */
    public static final InvocationHandler slf4jInfo() {
        return slf4j((logger, message) -> logger.info(message));
    }

    /**
     * Creates a Slf4j invocation handler that accepts a custom sink such as:
     * <code>(logger, message) -> logger.info(message)</code>. The message is constructed using
     * {@link StandardInvocationHandlers#stringFormat(java.util.function.Consumer)}
     *
     * @param sink
     *            The sink to use.
     * @return An invocation handler that prints method invocations to Slf4j
     */
    public static final InvocationHandler slf4j(final BiConsumer<Logger, String> sink) {
        return stringFormat(Slf4jSinks.slf4j(sink));
    }

}
