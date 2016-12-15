package de.xn__ho_hia.interesting.handler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Parameter;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import de.xn__ho_hia.interesting.converter.InvocationConverter;
import de.xn__ho_hia.interesting.converter.StandardConverters;
import de.xn__ho_hia.interesting.filter.DelegatingInvocationFilter;
import de.xn__ho_hia.interesting.sink.StandardSinks;

/**
 * Factory for standard {@link InvocationHandler}s.
 */
public final class StandardInvocationHandlers {

    /** Default String format template. */
    public static final String FORMAT_TEMPLATE     = "Class: [%s] Method: [%s] Arguments: %s"; //$NON-NLS-1$

    /** The default name/value template for String based converters. */
    public static final String NAME_VALUE_TEMPLATE = "%s: %s";                                 //$NON-NLS-1$

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
     * Formats incoming messages according to the given format template. Exposes the following three parameters to
     * templates:
     * <ul>
     * <li>Fully qualified class name of the POI.</li>
     * <li>Name of the calling method.</li>
     * <li>List of name/value pairs.</li>
     * </ul>
     *
     * @param template
     *            The format template to use.
     * @param sink
     *            The output sink to use.
     * @return An invocation handler that writes method invocations into the given sink.
     */
    public static final InvocationHandler stringFormat(final String template, final Consumer<String> sink) {
        return generic(StandardConverters.stringFormat(template, standardArgumentsConverter()), sink);
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
        return new GenericInvocationHandler<>(
                new DelegatingInvocationFilter(new ArrayList<>()),
                converter,
                sink,
                new HashMap<>());
    }

    /**
     * @return Converter that matches {@link StandardInvocationHandlers#FORMAT_TEMPLATE}
     */
    @SuppressWarnings("null")
    public static InvocationConverter<Object[]> standardArgumentsConverter() {
        return (proxy, method, args, extras) -> new Object[] {
                method.getDeclaringClass().getName(),
                method.getName(),
                combineNamesAndValues(method.getParameters(), args, extras)
        };
    }

    private static String combineNamesAndValues(final Parameter[] parameters, final Object[] args,
            final Map<String, Supplier<Object>> extras) {
        final String[] namesAndValues = new String[args.length + extras.size()];
        for (int index = 0; index < args.length; index++) {
            namesAndValues[index] = String.format(NAME_VALUE_TEMPLATE, parameters[index].getName(), args[index]);
        }
        int extraIndex = args.length;
        for (final Entry<String, Supplier<Object>> entry : extras.entrySet()) {
            namesAndValues[extraIndex] = String.format(NAME_VALUE_TEMPLATE, entry.getKey(), entry.getValue().get());
            extraIndex++;
        }
        return arrayToString(namesAndValues);
    }

    @SuppressWarnings({ "nls", "null" })
    private static final String arrayToString(final String[] arguments) {
        return Arrays.stream(arguments)
                .collect(Collectors.joining(", ", "[", "]"));
    }

}
