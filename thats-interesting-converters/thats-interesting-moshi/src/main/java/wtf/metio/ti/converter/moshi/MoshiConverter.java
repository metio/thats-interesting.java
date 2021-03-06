/*
 * This file is part of TI. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of TI,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */

package wtf.metio.ti.converter.moshi;

import com.squareup.moshi.Moshi;
import wtf.metio.ti.converter.InvocationConverter;

import java.io.StringWriter;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.function.Supplier;

/**
 * A converter that formats a method invocation using a {@link Moshi} instance.
 */
public final class MoshiConverter implements InvocationConverter<String> {

    private final Moshi moshi;

    /**
     * @param moshi The Moshi instance to use.
     */
    public MoshiConverter(final Moshi moshi) {
        this.moshi = moshi;
    }

    @Override
    public String convert(final Object proxy, final Method method, final Object[] args,
                          final Map<String, Supplier<Object>> extras) {
        final StringWriter stringWriter = new StringWriter();
        moshi.adapter(Object.class);
        // final Parameter[] parameters = method.getParameters();
        //
        // try (final JsonWriter jsonWriter = moshi.newJsonWriter(stringWriter)) {
        // jsonWriter.beginObject();
        // writeParameters(jsonWriter, parameters, args);
        // writeExtras(jsonWriter, extras);
        // jsonWriter.endObject();
        // jsonWriter.close();
        // } catch (final IOException exception) {
        // throw new IllegalStateException(exception);
        // }

        return stringWriter.toString();
    }
    //
    // private void writeParameters(final JsonWriter jsonWriter, final Parameter[] parameters, final Object[] args)
    // throws IOException {
    // for (int index = 0; index < args.length; index++) {
    // jsonWriter.name(parameters[index].getName());
    // jsonWriter.jsonValue(moshi.toJson(args[index]));
    // }
    // }
    //
    // private void writeExtras(final JsonWriter jsonWriter, final Map<String, Supplier<Object>> extras)
    // throws IOException {
    // for (final Entry<String, Supplier<Object>> entry : extras.entrySet()) {
    // jsonWriter.name(entry.getKey());
    // jsonWriter.jsonValue(moshi.toJson(entry.getValue().get()));
    // }
    // }

}
