package de.xn__ho_hia.interesting.converter;

import java.io.StringWriter;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Supplier;

import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonValue;
import javax.json.spi.JsonProvider;

/**
 * A converter that formats a method invocation using a JSR-353 {@link JsonProvider}.
 */
public final class JSR353Converter implements InvocationConverter<String> {

    private final JsonProvider jsonProvider;

    /**
     * @param jsonProvider
     *            The JSON provider to use.
     */
    public JSR353Converter(final JsonProvider jsonProvider) {
        this.jsonProvider = jsonProvider;
    }

    @Override
    public String convert(final Object proxy, final Method method, final Object[] args,
            final Map<String, Supplier<Object>> extras) {
        final JsonObject objectNode = createOutputModel(method, args, extras);
        return convertToString(objectNode);
    }

    @SuppressWarnings({ "nls", "null" })
    private JsonObject createOutputModel(final Method method, final Object[] args,
            final Map<String, Supplier<Object>> extras) {
        return jsonProvider.createObjectBuilder()
                .add("class", method.getDeclaringClass().getName())
                .add("method", method.getName())
                .add("arguments", createArgumentsModel(method.getParameters(), args))
                .add("extras", createExtrasModel(extras))
                .build();
    }

    @SuppressWarnings({ "null", "unused" })
    private JsonValue createArgumentsModel(final Parameter[] parameters, final Object[] args) {
        final JsonObjectBuilder objectBuilder = jsonProvider.createObjectBuilder();
        for (final Object arg : args) {
            // jsonProvider.createReader(new StringReader("{}"));
            // objectBuilder.add(parameters[index].getName(), args[index]);
        }

        return objectBuilder.build();
    }

    @SuppressWarnings({ "null", "unused" })
    private JsonValue createExtrasModel(final Map<String, Supplier<Object>> extras) {
        final JsonObjectBuilder objectBuilder = jsonProvider.createObjectBuilder();
        for (final Entry<String, Supplier<Object>> entry : extras.entrySet()) {
            // objectBuilder.add(entry.getKey(), entry.getValue().get());
        }

        return objectBuilder.build();
    }

    @SuppressWarnings("null")
    private String convertToString(final JsonObject jsonObject) {
        final StringWriter stringWriter = new StringWriter();
        jsonProvider.createWriter(stringWriter).writeObject(jsonObject);
        return stringWriter.toString();
    }

}
