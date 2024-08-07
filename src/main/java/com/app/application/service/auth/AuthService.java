package com.app.application.service.auth;

import com.app.application.business.UserBusiness;
import com.app.application.service.UserService;
import com.app.domain.database.entity.user.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author davi
 */
@Service
public class AuthService implements IAuth {
    @Autowired
    private UserService userService;
        
    public AuthService(UserService userService) {
        this.userService = userService;
    }    

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
        } catch(NotFoundException err) {
            err.printStackTrace();
            throw new UnauthorizedAuthenticationException(); 
        }
    }
}
