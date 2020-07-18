/*
 * This file is part of TI. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of TI,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */

package wtf.metio.ti.converter.gson;

import com.google.gson.Gson;
import wtf.metio.ti.converter.InvocationConverter;

/**
 * Factory for Gson based converters.
 */
public final class GsonConverters {

    /**
     * @return The configured method invocation converter.
     */
    public static InvocationConverter<String> jsonWithGson() {
        return jsonWithGson(new Gson());
    }

    /**
     * @param gson The Gson instance to use.
     * @return The configured method invocation converter.
     */
    public static InvocationConverter<String> jsonWithGson(final Gson gson) {
        return new GsonConverter(gson);
    }

}
