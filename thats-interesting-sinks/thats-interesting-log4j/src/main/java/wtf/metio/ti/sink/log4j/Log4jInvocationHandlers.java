/*
 * This file is part of TI. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of TI,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */

package wtf.metio.ti.sink.log4j;

import org.apache.logging.log4j.Logger;

import java.lang.reflect.InvocationHandler;
import java.util.function.BiConsumer;

import static wtf.metio.ti.handler.StandardInvocationHandlers.stringFormat;

/**
 * Factory for Log4j based {@link InvocationHandler}s.
 */
public final class Log4jInvocationHandlers {

    /**
     * @return An invocation handler that prints method invocations to Log4j
     */
    public static InvocationHandler log4jInfo() {
        return log4j(Logger::info);
    }

    /**
     * @return An invocation handler that prints method invocations to Log4j
     */
    public static InvocationHandler log4jWarning() {
        return log4j(Logger::warn);
    }

    /**
     * @return An invocation handler that prints method invocations to Log4j
     */
    public static InvocationHandler log4jDebug() {
        return log4j(Logger::debug);
    }

    /**
     * @return An invocation handler that prints method invocations to Log4j
     */
    public static InvocationHandler log4jError() {
        return log4j(Logger::error);
    }

    /**
     * @return An invocation handler that prints method invocations to Log4j
     */
    public static InvocationHandler log4jTraceEntry() {
        return log4j(Logger::traceEntry);
    }

    /**
     * @return An invocation handler that prints method invocations to Log4j
     */
    public static InvocationHandler log4jTraceExit() {
        return log4j(Logger::traceExit);
    }

    /**
     * @return An invocation handler that prints method invocations to Log4j
     */
    public static InvocationHandler log4jTrace() {
        return log4j(Logger::trace);
    }

    /**
     * @return An invocation handler that prints method invocations to Log4j
     */
    public static InvocationHandler log4jFatal() {
        return log4j(Logger::fatal);
    }

    /**
     * @param sink The sink to use.
     * @return An invocation handler that prints method invocations to Log4j
     */
    public static InvocationHandler log4j(final BiConsumer<Logger, String> sink) {
        return stringFormat(Log4jSinks.log4j(sink));
    }

}
