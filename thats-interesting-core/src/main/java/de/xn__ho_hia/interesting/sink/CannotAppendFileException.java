package de.xn__ho_hia.interesting.sink;

/**
 * Signals that a file cannot be appended with new data.
 */
public final class CannotAppendFileException extends RuntimeException {

    private static final long serialVersionUID = -4669042544071294293L;

    /**
     * @param exception
     *            The reason this exception was triggered.
     */
    public CannotAppendFileException(final Exception exception) {
        super(exception);
    }

}
