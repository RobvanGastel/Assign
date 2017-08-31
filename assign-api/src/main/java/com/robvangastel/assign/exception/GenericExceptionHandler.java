package com.robvangastel.assign.exception;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @author Rob van Gastel
 */

@Provider
public class GenericExceptionHandler implements ExceptionMapper<Throwable> {
    @Override
    public Response toResponse(Throwable throwable) {
        ErrorMessage errorMessage = new ErrorMessage();
        setHttpStatus(throwable, errorMessage);

        errorMessage.setMessage(throwable.getMessage());

        StringWriter errorStackTrace = new StringWriter();
        throwable.printStackTrace(new PrintWriter(errorStackTrace));
         errorMessage.setStacktrace(errorStackTrace.toString()); // Disable Stacktrace on Live build

        return Response.status(errorMessage.getStatus())
                .entity(errorMessage)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

    /***
     * Sets the status of an error response.
     */
    private void setHttpStatus(Throwable throwable, ErrorMessage errorMessage) {
        if (throwable instanceof UserException) {
            errorMessage.setStatus(Response.Status.BAD_REQUEST.getStatusCode());
        } else {
            errorMessage.setStatus(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
        }
    }
}
