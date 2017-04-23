package com.fontys.overheid.security;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * Created by robvangastel on 04/04/2017.
 */
@Getter
@ToString
@NoArgsConstructor
public class IdToken implements Serializable {

    private static final long serialVersionUID = 1L;

    public IdToken(String token) {
        this.token = token;
    }

    @JsonProperty("id_token")
    private String token;
}