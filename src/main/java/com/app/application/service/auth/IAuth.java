package com.app.application.service.auth;

import com.app.domain.database.entity.user.UserEntity;

public interface IAuth {
    /**
     * Dictate if user can authenticate using email and password. 
     * 
     * @param email UThe user email.
     * @param password The user password.
     * @return boolean
     */
    public UserEntity authenticate(String email, String password) throws UnauthorizedAuthenticationException;
}