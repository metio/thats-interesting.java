package de.xn__ho_hia.interesting.sink;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.function.Consumer;

/**
 * Factory for standard sinks.
 */
public class StandardSinks {

    /**
     * @return A consumer that uses System.out
     */
    public static final Consumer<String> systemOut() {
        return System.out::println;
    }

    /**
     * @return A consumer that uses System.err
     */
    public static final Consumer<String> systemError() {
        return System.err::println;
    }

    /**
     * @param logFile
     *            The file to write into.
     * @return A consumer that writes into a log file.
     */
    @SuppressWarnings("null")
    public static final Consumer<String> fileAppender(final Path logFile) {
        return fileAppender(logFile, UTF_8);
    }

    /**
     * @param logFile
     *            The file to write into.
     * @param charset
     *            The charset to use.
     * @return A consumer that writes into a log file.
     */
    @SuppressWarnings("nls")
    public static final Consumer<String> fileAppender(final Path logFile, final Charset charset) {
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

}
