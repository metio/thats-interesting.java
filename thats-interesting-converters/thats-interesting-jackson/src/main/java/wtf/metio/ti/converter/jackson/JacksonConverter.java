/*
 * This file is part of TI. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of TI,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */

package wtf.metio.ti.converter.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import wtf.metio.ti.converter.InvocationConverter;
import wtf.metio.ti.converter.StandardConverters;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * A converter that formats a method invocation using a Jackson {@link ObjectMapper}.
 */
public final class JacksonConverter implements InvocationConverter<String> {

    private final ObjectMapper objectMapper;

    /**
     * @param objectMapper The object mapper to use.
     */
    public JacksonConverter(final ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
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

    private ObjectNode createOutputModel(
            final Method method,
            final Object[] args,
            final Map<String, Supplier<Object>> extras) {
        final var rootNode = objectMapper.createObjectNode();

        rootNode.put(StandardConverters.CLASS, StandardConverters.getPOIName(method));
        rootNode.put(StandardConverters.METHOD, StandardConverters.getPOIMethodName(method));
        rootNode.putPOJO(StandardConverters.ARGUMENTS, createArgumentsModel(method.getParameters(), args));

        for (final var entry : extras.entrySet()) {
            rootNode.putPOJO(entry.getKey(), entry.getValue().get());
        }

        return rootNode;
    }

    private static Map<String, Object> createArgumentsModel(final Parameter[] parameters, final Object[] args) {
        final var argumentsMap = new LinkedHashMap<String, Object>(parameters.length);
        for (int index = 0; index < args.length; index++) {
            argumentsMap.put(parameters[index].getName(), args[index]);
        }
        return argumentsMap;
    }

    private String convertToString(final ObjectNode objectNode) {
        try {
            return objectMapper.writeValueAsString(objectNode);
        } catch (final JsonProcessingException exception) {
            throw new IllegalStateException(exception);
        }
    }

}
