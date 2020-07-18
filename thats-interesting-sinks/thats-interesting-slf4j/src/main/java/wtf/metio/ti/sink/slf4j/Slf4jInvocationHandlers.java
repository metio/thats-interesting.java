package wtf.metio.ti.sink.slf4j;

import org.slf4j.Logger;
import wtf.metio.ti.handler.StandardInvocationHandlers;

import java.lang.reflect.InvocationHandler;
import java.util.function.BiConsumer;

import static wtf.metio.ti.handler.StandardInvocationHandlers.stringFormat;

/**
 * Factory for Slf4j based {@link InvocationHandler}s.
 */
public final class Slf4jInvocationHandlers {

    /**
     * @return An invocation handler that prints method invocations to Slf4j
     */
    public static InvocationHandler slf4jInfo() {
        return slf4j(Logger::info);
    }

    /**
     * Creates a Slf4j invocation handler that accepts a custom sink such as:
     * <code>(logger, message) -> logger.info(message)</code>. The message is constructed using
     * {@link StandardInvocationHandlers#stringFormat(java.util.function.Consumer)}
     *
     * @param sink The sink to use.
     * @return An invocation handler that prints method invocations to Slf4j
     */
    public static InvocationHandler slf4j(final BiConsumer<Logger, String> sink) {
        return stringFormat(Slf4jSinks.slf4j(sink));
    }

}
