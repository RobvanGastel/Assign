package com.robvangastel.assign.security.jwt;

import com.robvangastel.assign.domain.Role;
import com.robvangastel.assign.security.UserPrincipal;

import java.util.List;

/**
 * Created by robvangastel on 04/04/2017.
 */

public class JwtUser implements UserPrincipal {

    private final String username;
    private final List<Role> roles;

    public JwtUser(String username, List<Role> roles) {
        this.username = username;
        this.roles = roles;
    }

    @Override
    public List<Role> getRoles() {
        return this.roles;
    }

    @Override
    public String getName() {
        return this.username;
    }

}