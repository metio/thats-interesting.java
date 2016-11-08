package de.xn__ho_hia.interesting.sink;

import java.util.function.Consumer;

import org.pmw.tinylog.Logger;

/**
 * Factory for tinylog sinks.
 */
public class TinylogSinks {

    /**
     * The class of the logger which writes into the sink.
     *
     * @return A consumer that uses tinylog
     */
    public static final Consumer<String> tinylog() {
        return tinylog(Logger::info);
    }

    /**
     * @param sink
     *            The sink to use.
     * @return A consumer that uses tinylog
     */
    public static final Consumer<String> tinylog(final Consumer<String> sink) {
        return message -> sink.accept(message);
    }

}
