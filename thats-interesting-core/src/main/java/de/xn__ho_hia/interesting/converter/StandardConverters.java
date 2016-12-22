package de.xn__ho_hia.interesting.converter;

import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Factory for standard converters.
 */
public final class StandardConverters {

    /** Default String format template. */
    public static final String FORMAT_TEMPLATE     = "Class: [%s] Method: [%s] Arguments: %s Extras: %s"; //$NON-NLS-1$

    /** The default name/value template for String based converters. */
    public static final String NAME_VALUE_TEMPLATE = "%s: %s";                                            //$NON-NLS-1$

    /**
     * @return The configured method invocation converter using {@link #FORMAT_TEMPLATE and
     *         #standardArgumentsConverter()}.
     */
    public static final InvocationConverter<String> stringFormat() {
        return new StringFormatConverter(FORMAT_TEMPLATE, standardArgumentsConverter());
    }

    /**
     * @param template
     *            The format template to use.
     * @return The configured method invocation converter.
     */
    public static final InvocationConverter<String> stringFormat(final String template) {
        return new StringFormatConverter(template, standardArgumentsConverter());
    }

    /**
     * @param template
     *            The format template to use.
     * @param argumentConverter
     *            The argument converter to use.
     * @return The configured method invocation converter.
     */
    public static final InvocationConverter<String> stringFormat(
            final String template,
            final InvocationConverter<Object[]> argumentConverter) {
        return new StringFormatConverter(template, argumentConverter);
    }

    /**
     * @return Converter that matches {@link #FORMAT_TEMPLATE}
     */
    @SuppressWarnings("null")
    public static InvocationConverter<Object[]> standardArgumentsConverter() {
        return (proxy, method, args, extras) -> new Object[] {
                method.getDeclaringClass().getName(),
                method.getName(),
                combineNamesAndValues(method.getParameters(), args),
                mapToString(extras)
        };
    }

    @SuppressWarnings("null")
    private static String combineNamesAndValues(final Parameter[] parameters, final Object[] args) {
        final String[] namesAndValues = new String[args.length];
        for (int index = 0; index < args.length; index++) {
            namesAndValues[index] = formatNameAndValue(parameters[index].getName(), args[index]);
        }
        return arrayToString(namesAndValues);
    }

    @SuppressWarnings("null")
    private static final String mapToString(final Map<String, Supplier<Object>> extras) {
        return streamToString(extras.entrySet().stream()
                .map(entry -> formatNameAndValue(entry.getKey(), entry.getValue().get())));
    }

    @SuppressWarnings("null")
    private static String formatNameAndValue(final String name, final Object value) {
        return String.format(NAME_VALUE_TEMPLATE, name, value);
    }

    @SuppressWarnings({ "null" })
    private static final String arrayToString(final String[] arguments) {
        return streamToString(Arrays.stream(arguments));
    }

    @SuppressWarnings({ "nls", "null" })
    private static final String streamToString(final Stream<String> stream) {
        return stream.collect(Collectors.joining(", ", "[", "]"));
    }

    private StandardConverters() {
        // factory class
    }

}
