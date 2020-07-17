package wtf.metio.ti.converter.xstream;

import com.thoughtworks.xstream.XStream;
import wtf.metio.ti.converter.InvocationConverter;
import wtf.metio.ti.converter.xstream.internal.MethodInvocationModel;

import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * A converter that formats a method invocation using a XStream.
 */
public final class XStreamConverter implements InvocationConverter<String> {

    private final XStream xstream;

    /**
     * @param xstream The xstream instance to use.
     */
    public XStreamConverter(final XStream xstream) {
        this.xstream = xstream;
    }

    @Override
    public String convert(
            final Object proxy,
            final Method method,
            final Object[] args,
            final Map<String, Supplier<Object>> extras) {
        final var parameters = method.getParameters();
        final var namesAndValues = new LinkedHashMap<String, Object>();
        for (int index = 0; index < args.length; index++) {
            namesAndValues.put(parameters[index].getName(), args[index]);
        }
        return xstream.toXML(new MethodInvocationModel(method, namesAndValues, extras));
    }

}
