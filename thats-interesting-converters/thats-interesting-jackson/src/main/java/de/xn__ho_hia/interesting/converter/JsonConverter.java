package de.xn__ho_hia.interesting.converter;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * A converter that formats a method invocation using a Jackson {@link ObjectMapper}.
 */
public final class JsonConverter implements InvocationConverter<String> {

    private final ObjectMapper objectMapper;

    /**
     * @param objectMapper
     *            The object mapper to use.
     */
    public JsonConverter(final ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public String convert(final Object proxy, final Method method, final Object[] args) {
        final ObjectNode objectNode = createOutputModel(method, args);
        return convertToString(objectNode);
    }

    @SuppressWarnings("null")
    private String convertToString(final ObjectNode objectNode) {
        try {
            return objectMapper.writeValueAsString(objectNode);
        } catch (final JsonProcessingException exception) {
            throw new IllegalStateException(exception);
        }
    }

    @SuppressWarnings("null")
    private ObjectNode createOutputModel(final Method method, final Object[] args) {
        final Parameter[] parameters = method.getParameters();
        final ObjectNode objectNode = objectMapper.createObjectNode();

        for (int index = 0; index < args.length; index++) {
            objectNode.putPOJO(parameters[index].getName(), args[index]);
        }

        return objectNode;
    }

}
