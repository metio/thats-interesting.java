/*
 * This file is part of TI. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of TI,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */

package wtf.metio.ti.sink.jul;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.logging.Logger;

/**
 * Factory for java.util.logging based sinks.
 */
public final class JULSinks {

    /**
     * @return A consumer that uses java.util.logging
     */
    public static Consumer<String> globalLogger() {
        return globalLogger(Logger::info);
    }

    /**
     * @param sink The sink to use.
     * @return A consumer that uses java.util.logging
     */
    public static Consumer<String> globalLogger(final BiConsumer<Logger, String> sink) {
        return logger(Logger.getGlobal(), sink);
    }

    /**
     * @param logger The logger to use.
     * @param sink   The sink to use.
     * @return A consumer that uses java.util.logging
     */
    public static Consumer<String> logger(final Logger logger, final BiConsumer<Logger, String> sink) {
        return msg -> sink.accept(logger, msg);
    }

}
