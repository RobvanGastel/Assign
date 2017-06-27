package com.robvangastel.assign.crypto;

/**
 *
 * @author Rob van Gastel
 */
 
public interface PasswordEncoder {

    public String encode(CharSequence rawPassword);

    public boolean matches(CharSequence rawPassword, String encodedPassword);

}
