package de.xn__ho_hia.interesting.converter;

import java.io.IOException;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Supplier;

import com.google.gson.Gson;
import com.google.gson.stream.JsonWriter;

/**
 * A converter that formats a method invocation using a Gson instance.
 */
public final class GsonConverter implements InvocationConverter<String> {

    private final Gson gson;

    /**
     * @param gson
     *            The Gson instance to use.
     */
    public GsonConverter(final Gson gson) {
        this.gson = gson;
    }

    @Override
    @SuppressWarnings("null")
    public String convert(final Object proxy, final Method method, final Object[] args,
            final Map<String, Supplier<Object>> extras) {
        final StringWriter stringWriter = new StringWriter();
        final Parameter[] parameters = method.getParameters();

        try (final JsonWriter jsonWriter = gson.newJsonWriter(stringWriter)) {
            jsonWriter.beginObject();
            writeParameters(jsonWriter, parameters, args);
            writeExtras(jsonWriter, extras);
            jsonWriter.endObject();
            jsonWriter.close();
        } catch (final IOException exception) {
            throw new IllegalStateException(exception);
        }

        return stringWriter.toString();
    }

    private void writeParameters(final JsonWriter jsonWriter, final Parameter[] parameters, final Object[] args)
            throws IOException {
        for (int index = 0; index < args.length; index++) {
            jsonWriter.name(parameters[index].getName());
            jsonWriter.jsonValue(gson.toJson(args[index]));
        }
    }

    private void writeExtras(final JsonWriter jsonWriter, final Map<String, Supplier<Object>> extras)
            throws IOException {
        for (final Entry<String, Supplier<Object>> entry : extras.entrySet()) {
            jsonWriter.name(entry.getKey());
            jsonWriter.jsonValue(gson.toJson(entry.getValue().get()));
        }
    }

}
