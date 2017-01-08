package com.robvangastel.assign.api.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 *
 * @author Rob
 */

@Entity
public class Account implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    public Long getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    @JsonIgnore
    private String password;
    private String username;

    public Account(String name, String password) {
        this.username = name;
        this.password = password;
    }

    Account() { // jpa only
    }
}