package com.robvangastel.assign.security;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

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

    public Credentials(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
