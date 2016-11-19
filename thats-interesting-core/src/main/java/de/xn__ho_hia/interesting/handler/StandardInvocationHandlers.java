package de.xn__ho_hia.interesting.handler;

import java.lang.reflect.InvocationHandler;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.function.Consumer;

import de.xn__ho_hia.interesting.converter.InvocationConverter;
import de.xn__ho_hia.interesting.converter.StandardConverters;
import de.xn__ho_hia.interesting.filter.DelegatingInvocationFilter;
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
     *            The path to the target log file.
     * @return An invocation handler that prints method invocations to a log file.
     */
    public static final InvocationHandler logFile(final Path pathToFile) {
        return stringFormat(StandardSinks.fileAppender(pathToFile));
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
     * @param template
     *            The format template to use.
     * @param sink
     *            The output sink to use.
     * @return An invocation handler that writes method invocations into the given sink.
     * @see StandardConverters#stringFormat(String) for a list of available template parameters.
     */
    public static final InvocationHandler stringFormat(final String template, final Consumer<String> sink) {
        return generic(StandardConverters.stringFormat(template), sink);
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
            final InvocationConverter<Object[]> argumentConverter,
            final Consumer<String> sink) {
        return generic(StandardConverters.stringFormat(template, argumentConverter), sink);
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
            final InvocationConverter<OUTPUT> converter,
            final Consumer<OUTPUT> sink) {
        return new GenericInvocationHandler<>(new DelegatingInvocationFilter(new ArrayList<>()), converter, sink);
    }

}
