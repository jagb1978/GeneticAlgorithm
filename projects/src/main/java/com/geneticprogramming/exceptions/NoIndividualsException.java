package com.geneticprogramming.exceptions;

/**
 * @author Jose Gonzalez
 */
public class NoIndividualsException extends Exception {
    private static final long serialVersionUID = 1990844019852545755L;

    public NoIndividualsException() {}

    public NoIndividualsException(String message) {
        super(message);
    }

    public NoIndividualsException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoIndividualsException(Throwable cause) {
        super(cause);
    }
}
