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
 * A converter that formats a method invocation using a GSON instance.
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
            for (int index = 0; index < args.length; index++) {
                jsonWriter.name(parameters[index].getName());
                jsonWriter.jsonValue(gson.toJson(args[index]));
            }
            for (final Entry<String, Supplier<Object>> entry : extras.entrySet()) {
                jsonWriter.name(entry.getKey());
                jsonWriter.jsonValue(gson.toJson(entry.getValue().get()));
            }
            jsonWriter.endObject().close();
        } catch (final IOException e) {
            e.printStackTrace();
        }

        return stringWriter.toString();
    }

}
