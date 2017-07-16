package com.robvangastel.assign;

import java.security.SecureRandom;

/**
 * @author Rob van Gastel
 */

public class CodeGenerator {

    // Length of 62 characters
    static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    static SecureRandom rnd = new SecureRandom();
    private static CodeGenerator instance;

    public static CodeGenerator getInstance() {
        if (instance == null) {
            instance = new CodeGenerator();
        }
        return instance;
    }

    /***
     * Generates a random string of characters
     *
     * Generate the number of possible strings by 62*length.
     * @param length of the string
     * @return the random string
     */
    public String getCode(int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++)
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        return sb.toString();
    }
}
