package com.robvangastel.assign.security;

import com.robvangastel.assign.domain.Role;

import java.security.Principal;
import java.util.List;

/**
 *
 * @author Rob van Gastel
 */

public interface UserPrincipal extends Principal {

    List<Role> getRoles();
}
