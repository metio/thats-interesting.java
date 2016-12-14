package de.xn__ho_hia.interesting.converter;

import com.google.gson.Gson;

/**
 * Factory for Gson based converters.
 */
public final class GsonConverters {

    /**
     * @return The configured method invocation converter.
     */
    public static final InvocationConverter<String> json() {
        return json(new Gson());
    }

    /**
     * @param gson
     *            The Gson instance to use.
     * @return The configured method invocation converter.
     */
    public static final InvocationConverter<String> json(final Gson gson) {
        return new GsonConverter(gson);
    }

}
