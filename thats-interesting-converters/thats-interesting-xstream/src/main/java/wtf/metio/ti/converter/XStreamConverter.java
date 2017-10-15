package wtf.metio.ti.converter;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Supplier;

import com.thoughtworks.xstream.XStream;

import wtf.metio.ti.converter.InvocationConverter;
import wtf.metio.ti.converter.internal.MethodInvocationModel;

/**
 * A converter that formats a method invocation using a XStream.
 */
public final class XStreamConverter implements InvocationConverter<String> {

    private final XStream xstream;

    /**
     * @param xstream
     *            The xstream instance to use.
     */
    public XStreamConverter(final XStream xstream) {
        this.xstream = xstream;
    }

    @Override
    @SuppressWarnings("null")
    public String convert(final Object proxy, final Method method, final Object[] args,
            final Map<String, Supplier<Object>> extras) {
        final Parameter[] parameters = method.getParameters();
        final Map<String, Object> namesAndValues = new LinkedHashMap<>();
        for (int index = 0; index < args.length; index++) {
            namesAndValues.put(parameters[index].getName(), args[index]);
        }

        return xstream.toXML(new MethodInvocationModel(method, namesAndValues, extras));
    }

}
