package de.xn__ho_hia.interesting.converter;

import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * Factory for standard converters.
 */
public final class StandardConverters {

    /** Default String format template. */
    public static final String FORMAT_TEMPLATE     = "Class: [%s] Method: [%s] Arguments: %s"; //$NON-NLS-1$

    /** The default name/value template for String based converters. */
    public static final String NAME_VALUE_TEMPLATE = "%s: %s";                                 //$NON-NLS-1$

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
