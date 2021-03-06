/*
 * This file is part of TI. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of TI,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */

package wtf.metio.ti.converter.gson;

import com.google.gson.Gson;
import com.google.gson.stream.JsonWriter;
import wtf.metio.ti.converter.InvocationConverter;
import wtf.metio.ti.converter.StandardConverters;

import java.io.IOException;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Map;
import java.util.function.Supplier;

/**
 * A converter that formats a method invocation using a Gson instance.
 */
public final class GsonConverter implements InvocationConverter<String> {

    private final Gson gson;

    /**
     * @param gson The Gson instance to use.
     */
    public GsonConverter(final Gson gson) {
        this.gson = gson;
    }

    private static void writeClass(final JsonWriter jsonWriter, final Method method) throws IOException {
        jsonWriter.name(StandardConverters.CLASS).value(StandardConverters.getPOIName(method));
    }

    private static void writeMethod(final JsonWriter jsonWriter, final Method method) throws IOException {
        jsonWriter.name(StandardConverters.METHOD).value(StandardConverters.getPOIMethodName(method));
    }

    @Override
    public String convert(
            final Object proxy,
            final Method method,
            final Object[] args,
            final Map<String, Supplier<Object>> extras) {
        final var stringWriter = new StringWriter();
        final var parameters = method.getParameters();

        try (final var jsonWriter = gson.newJsonWriter(stringWriter)) {
            jsonWriter.beginObject();
            writeClass(jsonWriter, method);
            writeMethod(jsonWriter, method);
            writeParameters(jsonWriter, parameters, args);
            writeExtras(jsonWriter, extras);
            jsonWriter.endObject();
        } catch (final IOException exception) {
            throw new IllegalStateException(exception);
        }

        return stringWriter.toString();
    }

    private void writeParameters(final JsonWriter jsonWriter, final Parameter[] parameters, final Object[] args)
            throws IOException {
        jsonWriter.name(StandardConverters.ARGUMENTS);
        jsonWriter.beginObject();
        for (int index = 0; index < args.length; index++) {
            jsonWriter.name(parameters[index].getName());
            jsonWriter.jsonValue(gson.toJson(args[index]));
        }
        jsonWriter.endObject();
    }

    private void writeExtras(final JsonWriter jsonWriter, final Map<String, Supplier<Object>> extras)
            throws IOException {
        for (final var entry : extras.entrySet()) {
            jsonWriter.name(entry.getKey());
            jsonWriter.jsonValue(gson.toJson(entry.getValue().get()));
        }
    }

}
