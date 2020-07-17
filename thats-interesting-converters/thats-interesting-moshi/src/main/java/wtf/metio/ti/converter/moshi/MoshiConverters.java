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
