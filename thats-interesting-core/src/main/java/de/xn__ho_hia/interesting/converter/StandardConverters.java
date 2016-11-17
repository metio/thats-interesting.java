package de.xn__ho_hia.interesting.converter;

import java.lang.reflect.Parameter;

import com.github.sebhoss.utils.strings.S;

import de.xn__ho_hia.interesting.handler.StandardInvocationHandlers;

/**
 * Factory for standard converters.
 */
public class StandardConverters {

    /** The default name/value template for String based converters. */
    public static final String NAME_VALUE_TEMPLATE = "%s: %s"; //$NON-NLS-1$

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
     * @return The configured method invocation converter.
     */
    public static final MethodInvocationConverter<String> string(final String template) {
        return new StringFormatConverter(template, argumentsConverter());
    }

    /**
     * @param template
     *            The format template to use.
     * @param argumentConverter
     *            The argument converter to use.
     * @return The configured method invocation converter.
     */
    public static final MethodInvocationConverter<String> string(
            final String template,
            final MethodInvocationConverter<Object[]> argumentConverter) {
        return new StringFormatConverter(template, argumentConverter);
    }

    /**
     * Converter that matches {@link StandardInvocationHandlers#FORMAT_TEMPLATE}
     */
    @SuppressWarnings("null")
    private static MethodInvocationConverter<Object[]> argumentsConverter() {
        return (proxy, method, args) -> new Object[] {
                method.getDeclaringClass().getName(),
                method.getName(),
                combineNamesAndValues(method.getParameters(), args)
        };
    }

    @SuppressWarnings("null")
    private static String combineNamesAndValues(final Parameter[] parameters, final Object[] args) {
        final String[] namesAndValues = new String[args.length];
        for (int index = 0; index < args.length; index++) {
            namesAndValues[index] = String.format(NAME_VALUE_TEMPLATE, parameters[index].getName(), args[index]);
        }
        return S.arrayToString(namesAndValues);
    }

}
