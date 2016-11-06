package de.xn__ho_hia.interesting.converter;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

import com.github.sebhoss.utils.strings.S;

import de.xn__ho_hia.interesting.handler.StandardInvocationHandlers;

/**
 * Factory for standard converters.
 */
public class StandardConverters {

    private static final String NAME_VALUE_TEMPLATE = "%s: %s"; //$NON-NLS-1$

    /**
     * @param format
     *            The string format to use.
     * @return The configured method invocation converter.
     */
    public static final MethodInvocationConverter<String> string(final String format) {
        return new StringFormatConverter(format, argumentsConverter());
    }

    /**
     * @param format
     *            The string format to use.
     * @param argumentConverter
     *            The argument converter to use.
     * @return The configured method invocation converter.
     */
    public static final MethodInvocationConverter<String> string(
            final String format,
            final MethodInvocationConverter<Object[]> argumentConverter) {
        return new StringFormatConverter(format, argumentConverter);
    }

    /**
     * Converter that matches {@link StandardInvocationHandlers#FORMAT_TEMPLATE}
     */
    private static MethodInvocationConverter<Object[]> argumentsConverter() {
        return (proxy, method, args) -> new Object[] {
                method.getDeclaringClass().getName(),
                method.getName(),
                combineNamesAndValues(method, args)
        };
    }

    private static String combineNamesAndValues(final Method method, final Object[] args) {
        final Parameter[] parameters = method.getParameters();
        final String[] namesAndValues = new String[args.length];
        for (int index = 0; index < args.length; index++) {
            namesAndValues[index] = String.format(NAME_VALUE_TEMPLATE, parameters[index].getName(), args[index]);
        }
        return S.arrayToString(namesAndValues);
    }

}
