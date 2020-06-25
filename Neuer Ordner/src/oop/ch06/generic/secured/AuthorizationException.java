package oop.ch06.generic.secured;

/**
 * This class represents exceptions that are thrown whenever an authorization
 * fails.
 *
 * @author Mark Minas
 */
public class AuthorizationException extends Exception {
    /**
     * Constructs a new authorization exception with the specified detail message
     * and cause.
     *
     * @param message the detail message (which is saved for later retrieval by the
     *                {@link #getMessage()} method).
     */
    public AuthorizationException(String message) {
        super(message);
    }
}
