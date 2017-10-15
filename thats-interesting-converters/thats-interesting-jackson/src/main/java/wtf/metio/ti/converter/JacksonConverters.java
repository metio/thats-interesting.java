package wtf.metio.ti.converter;

import com.fasterxml.jackson.databind.ObjectMapper;

import wtf.metio.ti.converter.InvocationConverter;

/**
 * Factory for Jackson based converters.
 */
public final class JacksonConverters {

    /**
     * @return The configured method invocation converter.
     */
    public static final InvocationConverter<String> jsonWithJackson() {
        return jsonWithJackson(new ObjectMapper());
    }

    /**
     * @param objectMapper
     *            The object mapper to use.
     * @return The configured method invocation converter.
     */
    public static final InvocationConverter<String> jsonWithJackson(final ObjectMapper objectMapper) {
        return new JacksonConverter(objectMapper);
    }

    private JacksonConverters() {
        // factory class
    }

}
