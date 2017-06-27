package com.robvangastel.assign.exception;

import javax.ws.rs.core.Response;

/**
 *
 * @author Rob van Gastel
 */

public class ErrorMessage {

    private int status;
    private String message;
    private String stacktrace;

    public ErrorMessage() {}

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStacktrace() {
        return stacktrace;
    }

    public void setStacktrace(String stacktrace) {
        this.stacktrace = stacktrace;
    }
}
