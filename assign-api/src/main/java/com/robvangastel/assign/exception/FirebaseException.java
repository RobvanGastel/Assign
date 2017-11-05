package com.robvangastel.assign.exception;

/**
 * @author Rob van Gastel
 */
public class FirebaseException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public FirebaseException(String message) {
        super(message);
    }
}
