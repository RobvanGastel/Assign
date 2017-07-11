package com.robvangastel.assign.security;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author Rob van Gastel
 */

@Getter
@ToString
@NoArgsConstructor
public class IdToken implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("id_token")
    private String token;

    public IdToken(String token) {
        this.token = token;
    }
}
