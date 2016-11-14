package de.xn__ho_hia.interesting.sink;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
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
    public static final Consumer<String> fileAppender(final Path logFile) {
        return appender(logFile);
    }

    private static Consumer<String> appender(final Path logFile) {
        return message -> {
            try {
                @SuppressWarnings("nls")
                final String output = message.endsWith("\n") ? message : message + "\n";
                Files.write(logFile, output.getBytes(UTF_8), CREATE, APPEND);
            } catch (final IOException exception) {
                throw new RuntimeException(exception);
            }
        };
    }

}
