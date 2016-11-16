package de.xn__ho_hia.interesting.handler;

import static de.xn__ho_hia.interesting.converter.StandardConverters.string;

import java.lang.reflect.InvocationHandler;
import java.nio.file.Paths;
import java.util.function.Consumer;

import de.xn__ho_hia.interesting.converter.MethodInvocationConverter;
import de.xn__ho_hia.interesting.sink.StandardSinks;

/**
 * Factory for standard {@link InvocationHandler}s.
 */
public final class StandardInvocationHandlers {

    /** Default String format template. */
    public static final String FORMAT_TEMPLATE = "Class: [%s] Method: [%s] Arguments: %s"; //$NON-NLS-1$

    /**
     * @param handlers
     *            The invocation handlers to delegate to.
     * @return An delegating invocation handler that calls all given handlers in order they were given.
     */
    public static final InvocationHandler delegate(final InvocationHandler... handlers) {
        return new DelegatingInvocationHandler(handlers);
    }

    /**
     * @param pathToFile
     *            The absolute or relative path to the target log file.
     * @return An invocation handler that prints method invocations to a log file.
     */
    public static final InvocationHandler logFile(final String pathToFile) {
        return stringFormat(StandardSinks.fileAppender(Paths.get(pathToFile)));
    }

    /**
     * @return An invocation handler that prints method invocations to System.out
     */
    public static final InvocationHandler systemOut() {
        return stringFormat(StandardSinks.systemOut());
    }

    /**
     * @param sink
     *            The output sink to use.
     * @return An invocation handler that writes method invocations into the given sink.
     */
    public static final InvocationHandler stringFormat(final Consumer<String> sink) {
        return stringFormat(FORMAT_TEMPLATE, sink);
    }

    /**
     * TODO: document possible template placeholders
     *
     * @param template
     *            The format template to use.
     * @param sink
     *            The output sink to use.
     * @return An invocation handler that writes method invocations into the given sink.
     */
    public static final InvocationHandler stringFormat(final String template, final Consumer<String> sink) {
        return generic(string(template), sink);
    }

    /**
     * @param template
     *            The format template to use.
     * @param argumentConverter
     *            The argument convert to use.
     * @param sink
     *            The output sink to use.
     * @return An invocation handler that writes method invocations into the given sink.
     */
    public static final InvocationHandler stringFormat(
            final String template,
            final MethodInvocationConverter<Object[]> argumentConverter,
            final Consumer<String> sink) {
        return generic(string(template, argumentConverter), sink);
    }

    /**
     * @param converter
     *            The converter to use.
     * @param sink
     *            The output sink to use.
     * @return An invocation handler that converts method invocations with the given converter and writes the result
     *         into the given sink.
     */
    public static final <OUTPUT> InvocationHandler generic(
            final MethodInvocationConverter<OUTPUT> converter,
            final Consumer<OUTPUT> sink) {
        return new GenericInvocationHandler<>(converter, sink);
    }

}
