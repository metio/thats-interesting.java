package wtf.metio.ti.converter.jsr353;

import wtf.metio.ti.converter.InvocationConverter;
import wtf.metio.ti.converter.StandardConverters;

import javax.json.JsonObject;
import javax.json.JsonValue;
import javax.json.spi.JsonProvider;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Map;
import java.util.function.Supplier;

/**
 * A converter that formats a method invocation using a JSR-353 {@link JsonProvider}.
 */
public final class JSR353Converter implements InvocationConverter<String> {

    private final JsonProvider jsonProvider;

    /**
     * @param jsonProvider The JSON provider to use.
     */
    public JSR353Converter(final JsonProvider jsonProvider) {
        this.jsonProvider = jsonProvider;
    }

    @Override
    public String convert(
            final Object proxy,
            final Method method,
            final Object[] args,
            final Map<String, Supplier<Object>> extras) {
        final var objectNode = createOutputModel(method, args, extras);
        return convertToString(objectNode);
    }

    private JsonObject createOutputModel(
            final Method method,
            final Object[] args,
            final Map<String, Supplier<Object>> extras) {
        return jsonProvider.createObjectBuilder()
                .add(StandardConverters.CLASS, StandardConverters.getPOIName(method))
                .add(StandardConverters.METHOD, StandardConverters.getPOIMethodName(method))
                .add(StandardConverters.ARGUMENTS, createArgumentsModel(method.getParameters(), args))
                .add(StandardConverters.EXTRAS, createExtrasModel(extras))
                .build();
    }

    private JsonValue createArgumentsModel(final Parameter[] parameters, final Object[] args) {
        final var objectBuilder = jsonProvider.createObjectBuilder();
        for (final Object arg : args) {
            // jsonProvider.createReader(new StringReader("{}"));
            // objectBuilder.add(parameters[index].getName(), args[index]);
        }
        return objectBuilder.build();
    }

    private JsonValue createExtrasModel(final Map<String, Supplier<Object>> extras) {
        final var objectBuilder = jsonProvider.createObjectBuilder();
        for (final var entry : extras.entrySet()) {
            // objectBuilder.add(entry.getKey(), entry.getValue().get());
        }
        return objectBuilder.build();
    }

    private String convertToString(final JsonObject jsonObject) {
        final var stringWriter = new StringWriter();
        jsonProvider.createWriter(stringWriter).writeObject(jsonObject);
        return stringWriter.toString();
    }

}
