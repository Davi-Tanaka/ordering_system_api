package com.app.application.business;

import at.favre.lib.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UserBusiness {
    /**
     * Hash user password.
     * @param pswd
     * @return String
     */
    public static String hashPassword(String pswd) {
        byte[] pswdHashedInBytes = BCrypt.withDefaults().hash(13, pswd.toCharArray());
        return new String(pswdHashedInBytes);
    }
    
    /**
     * Compare two passwords then return true if the password matches.
     * @param rawPassword The raw password
     * @param encodedPassword The hashed password
     * @return boolean
     */
    
    public static boolean passwordMatches(String rawPassword, String encodedPassword) {
        BCryptPasswordEncoder b = new BCryptPasswordEncoder();
        return b.matches(rawPassword, encodedPassword);
    }
}
