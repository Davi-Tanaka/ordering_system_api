package com.app.application.service.auth;

/**
 *
 * @author davi
 */
public class UnauthorizedAuthenticationException extends Exception {
    private final static String defaultMessage = "Unauthorized authentication";
    
    public UnauthorizedAuthenticationException() {
        super(UnauthorizedAuthenticationException.defaultMessage);
    }
    
    public UnauthorizedAuthenticationException(String message) {
        super(message);
    }
}
