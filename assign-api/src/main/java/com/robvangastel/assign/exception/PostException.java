package com.robvangastel.assign.exception;

/**
 *
 * @author Rob van Gastel
 */

public class PostException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public PostException(String message) {
      super(message);
    }
}
