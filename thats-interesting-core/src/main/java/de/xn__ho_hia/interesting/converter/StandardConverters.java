package de.xn__ho_hia.interesting.converter;

/**
 * Factory for standard converters.
 */
public final class StandardConverters {

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

}
