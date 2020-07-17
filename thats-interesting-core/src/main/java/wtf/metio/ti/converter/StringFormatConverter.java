package wtf.metio.ti.converter;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.function.Supplier;

import static java.util.Objects.requireNonNull;

/**
 * A converter that formats a method invocation according to a string format template.
 */
public final class StringFormatConverter implements InvocationConverter<String> {

    private final String format;
    private final InvocationConverter<Object[]> formatArguments;

    /**
     * @param format          The format template to use.
     * @param formatArguments The format arguments converter to use.
     */
    public StringFormatConverter(
            final String format,
            final InvocationConverter<Object[]> formatArguments) {
        this.format = format;
        this.formatArguments = formatArguments;
    }

    @Override
    public String convert(
            final Object proxy,
            final Method method,
            final Object[] args,
            final Map<String, Supplier<Object>> extras) {
        return requireNonNull(String.format(format, formatArguments.convert(proxy, method, args, extras)));
    }

}
