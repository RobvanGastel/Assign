package com.robvangastel.assign.crypto;

/**
 *
 * @author Rob van Gastel
 */

public interface PasswordEncoder {

    /***
     * Encode the password
     * @return the encoded password String
     */
    public String encode(CharSequence rawPassword);

    /***
     * Match the encoded password to the raw password
     * @return Boolean whether the password matches 
     */
    public boolean matches(CharSequence rawPassword, String encodedPassword);

}
