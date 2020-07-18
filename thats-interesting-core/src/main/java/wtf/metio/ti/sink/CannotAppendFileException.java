/*
 * This file is part of TI. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of TI,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */

package wtf.metio.ti.sink;

/**
 * Signals that a file cannot be appended with new data.
 */
public final class CannotAppendFileException extends RuntimeException {

    private static final long serialVersionUID = -4669042544071294293L;

    /**
     * @param exception The reason this exception was triggered.
     */
    public CannotAppendFileException(final Exception exception) {
        super(exception);
    }

}
