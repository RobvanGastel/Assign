/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.robvangastel.assign.api.security;

import com.robvangastel.assign.api.domain.Account;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 * @author Rob
 */
public class CustomUserDetails implements UserDetails {
    
    private Account account;
 
    public CustomUserDetails(Account account) {
        this.account = account;
    }
    
    //TODO Implement methods
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        final List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        //final Privilege privilege : user.getPrivileges()
//        for () {
            authorities.add(new SimpleGrantedAuthority("USER_ROLE"));
//        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return account.getPassword();
    }

    public String getEmail() {
        return account.getEmail();
    }
    
    @Override
    public String getUsername() {
        return account.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
