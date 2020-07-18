/*
 * This file is part of TI. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of TI,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */

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
