package wtf.metio.ti.converter;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.function.Supplier;

/**
 * A converter for method invocations.
 *
 * @param <OUTPUT> The result type of the method invocation.
 */
@FunctionalInterface
public interface InvocationConverter<OUTPUT> {

    /**
     * @param proxy  The proxied logger interface.
     * @param method The invoked method.
     * @param args   The used method parameters.
     * @param extras The extra values to use.
     * @return The computed result.
     */
    OUTPUT convert(
            Object proxy,
            Method method,
            Object[] args,
            Map<String, Supplier<Object>> extras);

}
