package com.robvangastel.assign.exception;

/**
 * @author Rob van Gastel
 */

public class AuthenticationException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public AuthenticationException() {
    }

    public AuthenticationException(String message) {
        super(message);
    }
}
