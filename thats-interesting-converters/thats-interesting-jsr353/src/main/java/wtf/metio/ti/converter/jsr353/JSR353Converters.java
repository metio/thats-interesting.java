/*
 * This file is part of TI. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of TI,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */

package wtf.metio.ti.converter.jsr353;

import wtf.metio.ti.converter.InvocationConverter;

import javax.json.spi.JsonProvider;

/**
 * Factory for JSR-353 based converters.
 */
public final class JSR353Converters {

    private JSR353Converters() {
        // factory class
    }

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

}
