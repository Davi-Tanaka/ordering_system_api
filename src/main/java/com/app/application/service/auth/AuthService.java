package com.app.application.service.auth;

import com.app.exceptions.UnauthorizedAuthenticationException;
import com.app.application.business.UserBusiness;
import com.app.application.service.user.UserService;
import com.app.domain.database.entity.user.UserEntity;
import com.app.exceptions.RecordNotFoundInDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements IAuth {
    @Autowired
    private UserService userService;
        
    @Override
    public UserEntity authenticate(String email, String password) throws UnauthorizedAuthenticationException {
        try {
            UserEntity user = userService.findByEmail(email);
            boolean passwordMatches = UserBusiness.passwordMatches(password, user.getPassword());
            
            if(passwordMatches) {
                return user;
            } else {
                throw new UnauthorizedAuthenticationException();
            }
        } catch(RecordNotFoundInDatabase err) {
            throw new UnauthorizedAuthenticationException(); 
        }
    }
}
