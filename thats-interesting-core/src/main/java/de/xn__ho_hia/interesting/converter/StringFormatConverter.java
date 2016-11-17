package de.xn__ho_hia.interesting.converter;

import java.lang.reflect.Method;

/**
 * A converter that formats a method invocation according to a string format template.
 */
public class StringFormatConverter implements MethodInvocationConverter<String> {

    private final String                              format;
    private final MethodInvocationConverter<Object[]> formatArguments;

    /**
     * @param format
     *            The format template to use.
     * @param formatArguments
     *            The format arguments converter to use.
     */
    public StringFormatConverter(
            final String format,
            final MethodInvocationConverter<Object[]> formatArguments) {
        this.format = format;
        this.formatArguments = formatArguments;
    }

    @Override
    @SuppressWarnings("null")
    public String convert(final Object proxy, final Method method, final Object[] args) {
        return String.format(format, formatArguments.convert(proxy, method, args));
    }

}
