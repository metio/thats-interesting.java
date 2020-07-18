/*
 * This file is part of TI. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of TI,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */

package wtf.metio.ti.converter.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import wtf.metio.ti.converter.InvocationConverter;

/**
 * Factory for Jackson based converters.
 */
public final class JacksonConverters {

    /**
     * @return The configured method invocation converter.
     */
    public static InvocationConverter<String> jsonWithJackson() {
        return jsonWithJackson(new ObjectMapper());
    }

    /**
     * @param objectMapper The object mapper to use.
     * @return The configured method invocation converter.
     */
    public static InvocationConverter<String> jsonWithJackson(final ObjectMapper objectMapper) {
        return new JacksonConverter(objectMapper);
    }

    private JacksonConverters() {
        // factory class
    }

}
