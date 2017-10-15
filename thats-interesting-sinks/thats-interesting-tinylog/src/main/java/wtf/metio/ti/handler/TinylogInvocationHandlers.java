package wtf.metio.ti.handler;

import static wtf.metio.ti.handler.StandardInvocationHandlers.stringFormat;

import java.lang.reflect.InvocationHandler;
import java.util.function.Consumer;

import org.pmw.tinylog.Logger;

/**
 * Factory for tinylog based {@link InvocationHandler}s.
 */
public final class TinylogInvocationHandlers {

    /**
     * @return An invocation handler that prints method invocations to tinylog
     */
    public static InvocationHandler tinylogInfo() {
        return tinylog(Logger::info);
    }

    /**
     * @param sink
     *            The sink to use.
     * @return An invocation handler that prints method invocations to tinylog
     */
    public static InvocationHandler tinylog(final Consumer<String> sink) {
        return stringFormat(sink);
    }

}
