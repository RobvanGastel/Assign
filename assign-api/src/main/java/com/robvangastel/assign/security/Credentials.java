package com.robvangastel.assign.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 *
 * @author Rob van Gastel
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Credentials implements Serializable {

    private String username;
    private String password;
}
