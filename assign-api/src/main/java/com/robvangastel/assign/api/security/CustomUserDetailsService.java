package com.robvangastel.assign.api.security;

import com.robvangastel.assign.api.domain.Account;
import com.robvangastel.assign.api.repositories.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author Rob
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {
 
    @Autowired
    private AccountService accountService;
 
    @Override
    public UserDetails loadUserByUsername(String email) {
        Account account = accountService.findByEmail(email);
        if (account == null) {
            throw new UsernameNotFoundException(email);
        }
        return new CustomUserDetails(account);
    }
}
