/*
 * This file is part of TI. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of TI,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */

package wtf.metio.ti.converter.moshi;

import com.squareup.moshi.Moshi;
import wtf.metio.ti.converter.InvocationConverter;

/**
 * Factory for Moshi based converters.
 */
public final class MoshiConverters {

    /**
     * @return The configured method invocation converter.
     */
    public static InvocationConverter<String> jsonWithMoshi() {
        return jsonWithMoshi(new Moshi.Builder().build());
    }

    /**
     * @param moshi The Moshi instance to use.
     * @return The configured method invocation converter.
     */
    public static InvocationConverter<String> jsonWithMoshi(final Moshi moshi) {
        return new MoshiConverter(moshi);
    }

}
