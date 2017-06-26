package com.robvangastel.assign.security;

import com.robvangastel.assign.domain.Role;

import java.security.Principal;
import java.util.List;

/**
 * Created by robvangastel on 04/04/2017.
 */

public interface UserPrincipal extends Principal {

    List<Role> getRoles();
}

