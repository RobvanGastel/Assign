package com.robvangastel.assign.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
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
