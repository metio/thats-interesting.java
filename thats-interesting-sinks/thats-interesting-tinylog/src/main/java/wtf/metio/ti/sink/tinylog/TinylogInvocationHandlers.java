/*
 * This file is part of TI. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of TI,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */

package wtf.metio.ti.sink.tinylog;

import org.pmw.tinylog.Logger;

import java.lang.reflect.InvocationHandler;
import java.util.function.Consumer;

import static wtf.metio.ti.handler.StandardInvocationHandlers.stringFormat;

/**
 * Factory for tinylog based {@link InvocationHandler}s.
 */
public final class TinylogInvocationHandlers {

    /**
     * @return An invocation handler that prints method invocations to tinylog
     */
    public static InvocationHandler tinylogInfo() {
        return tinylog(Logger::info);
    }

    /**
     * @param sink The sink to use.
     * @return An invocation handler that prints method invocations to tinylog
     */
    public static InvocationHandler tinylog(final Consumer<String> sink) {
        return stringFormat(sink);
    }

}
