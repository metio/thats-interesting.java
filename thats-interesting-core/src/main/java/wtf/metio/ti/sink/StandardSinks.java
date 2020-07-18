/*
 * This file is part of TI. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of TI,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */

package wtf.metio.ti.sink;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.function.Consumer;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;

/**
 * Factory for standard sinks.
 */
public final class StandardSinks {

    /**
     * @return A consumer that uses System.out
     */
    public static Consumer<String> systemOut() {
        return System.out::println;
    }

    /**
     * @return A consumer that uses System.err
     */
    public static Consumer<String> systemError() {
        return System.err::println;
    }

    /**
     * @param logFile The file to write into.
     * @return A consumer that writes into a log file.
     */
    public static Consumer<String> fileAppender(final Path logFile) {
        return fileAppender(logFile, UTF_8);
    }

    /**
     * @param logFile The file to write into.
     * @param charset The charset to use.
     * @return A consumer that writes into a log file.
     */
    public static Consumer<String> fileAppender(final Path logFile, final Charset charset) {
        Objects.requireNonNull(logFile, "The given log file is NULL.");
        Objects.requireNonNull(charset, "The given charset is NULL.");

        return message -> {
            try {
                final String output = message.endsWith("\n") ? message : message + "\n";
                Files.write(logFile, output.getBytes(charset), CREATE, APPEND);
            } catch (final IOException exception) {
                throw new CannotAppendFileException(exception);
            }
        };
    }

    private StandardSinks() {
        // factory class
    }

}
