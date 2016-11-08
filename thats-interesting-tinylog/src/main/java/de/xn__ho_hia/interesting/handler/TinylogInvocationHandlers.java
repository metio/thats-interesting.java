package de.xn__ho_hia.interesting.handler;

import static de.xn__ho_hia.interesting.handler.StandardInvocationHandlers.stringFormat;

import java.lang.reflect.InvocationHandler;
import java.util.function.Consumer;

import org.pmw.tinylog.Logger;

import de.xn__ho_hia.interesting.sink.TinylogSinks;

/**
 * Factory for tinylog based {@link InvocationHandler}s.
 */
public final class TinylogInvocationHandlers {

    /**
     * @return An invocation handler that prints method invocations to tinylog
     */
    public static final InvocationHandler tinylogInfo() {
        return tinylog(Logger::info);
    }

    /**
     * @param sink
     *            The sink to use.
     * @return An invocation handler that prints method invocations to tinylog
     */
    public static final InvocationHandler tinylog(final Consumer<String> sink) {
        return stringFormat(TinylogSinks.tinylog(sink));
    }

}
