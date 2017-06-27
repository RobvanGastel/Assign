package com.robvangastel.assign.crypto.plain;

import com.robvangastel.assign.crypto.PasswordEncoder;

/**
 *
 * @author Rob van Gastel
 */
 
public class PlainPasswordEncoder implements PasswordEncoder {

    @Override
    public String encode(CharSequence rawPassword) {
        return rawPassword.toString();
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return rawPassword.equals(encodedPassword);
    }

}
