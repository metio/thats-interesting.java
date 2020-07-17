package wtf.metio.ti;

import wtf.metio.ti.builder.LoggerBuilder;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Factory class for new loggers.
 *
 * @see LoggerBuilder
 */
public final class Interested {

    /**
     * @param logger The logger interface to proxy.
     * @return A logger builder for the given interface.
     */
    public static <LOGGER> LoggerBuilder<LOGGER> in(final Class<LOGGER> logger) {
        return new LoggerBuilder<>(logger, new ArrayList<>(), new HashMap<>());
    }

}
