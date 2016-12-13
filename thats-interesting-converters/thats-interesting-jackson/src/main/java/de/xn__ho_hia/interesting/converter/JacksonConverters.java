package de.xn__ho_hia.interesting.converter;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Factory for Jackson based converters.
 */
public final class JacksonConverters {

    /**
     * @return The configured method invocation converter.
     */
    public static final InvocationConverter<String> json() {
        return json(new ObjectMapper());
    }

    /**
     * @param objectMapper
     *            The object mapper to use.
     * @return The configured method invocation converter.
     */
    public static final InvocationConverter<String> json(final ObjectMapper objectMapper) {
        return new JacksonConverter(objectMapper);
    }

}
