package de.xn__ho_hia.interesting.sink;

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

}
