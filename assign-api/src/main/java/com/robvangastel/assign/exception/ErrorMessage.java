package com.robvangastel.assign.exception;

import javax.ws.rs.core.Response;

/**
 *
 * @author Rob van Gastel
 */

 @Data
 @NoArgsConstructor
 @AllArgsConstructor
public class ErrorMessage {

    private int status;
    private String message;
    private String stacktrace;
    
}
