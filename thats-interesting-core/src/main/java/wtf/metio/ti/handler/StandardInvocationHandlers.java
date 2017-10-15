package wtf.metio.ti.handler;

import java.lang.reflect.InvocationHandler;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;

import wtf.metio.ti.converter.InvocationConverter;
import wtf.metio.ti.converter.StandardConverters;
import wtf.metio.ti.filter.DelegatingInvocationFilter;
import wtf.metio.ti.filter.InvocationFilter;
import wtf.metio.ti.sink.StandardSinks;

/**
 * Factory for standard {@link InvocationHandler}s.
 */
public final class StandardInvocationHandlers {

    /**
     * @param handlers
     *            The invocation handlers to delegate to.
     * @return An delegating invocation handler that calls all given handlers in order they were given.
     */
    @SuppressWarnings("null")
    public static final InvocationHandler delegate(final InvocationHandler... handlers) {
        return delegate(Arrays.asList(handlers));
    }

    /**
     * @param handlers
     *            The invocation handlers to delegate to.
     * @return An delegating invocation handler that calls all given handlers in order they were given.
     */
    public static final InvocationHandler delegate(final List<InvocationHandler> handlers) {
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
        return generic(StandardConverters.stringFormat(), sink);
    }

    /**
     * @param template
     *            The format template to use.
     * @param sink
     *            The output sink to use.
     * @return An invocation handler that writes method invocations into the given sink.
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
        return generic(
                new DelegatingInvocationFilter(new ArrayList<>()),
                converter,
                sink,
                new HashMap<>());
    }

    /**
     * @param filter
     *            The filter to use.
     * @param converter
     *            The converter to use.
     * @param sink
     *            The output sink to use.
     * @param extras
     *            The extras to apply.
     * @return An invocation handler that converts method invocations with the given converter and writes the result
     *         into the given sink.
     */
    public static final <OUTPUT> InvocationHandler generic(
            final InvocationFilter filter,
            final InvocationConverter<OUTPUT> converter,
            final Consumer<OUTPUT> sink,
            final Map<String, Supplier<Object>> extras) {
        return new GenericInvocationHandler<>(
                filter,
                converter,
                sink,
                extras);
    }

    private StandardInvocationHandlers() {
        // factory class
    }

}
