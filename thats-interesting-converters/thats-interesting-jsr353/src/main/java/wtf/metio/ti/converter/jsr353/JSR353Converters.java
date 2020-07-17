package wtf.metio.ti.converter.jsr353;

import wtf.metio.ti.converter.InvocationConverter;

import javax.json.spi.JsonProvider;

/**
 * Factory for JSR-353 based converters.
 */
public final class JSR353Converters {

    /**
     * @return The configured method invocation converter.
     */
    public static InvocationConverter<String> jsonWithJsr353() {
        return jsonWithJsr353(JsonProvider.provider());
    }

    /**
     * @param jsonProvider The JSON provider to use.
     * @return The configured method invocation converter.
     */
    public static InvocationConverter<String> jsonWithJsr353(final JsonProvider jsonProvider) {
        return new JSR353Converter(jsonProvider);
    }

    private JSR353Converters() {
        // factory class
    }

}
