package share.mock.service;

import com.app.application.service.UserService;
import com.app.application.service.auth.AuthService;
import share.mock.repository.UserRepositoryMock;

public class AuthServiceMock {
    public static AuthService getMock() {
        return new AuthService(new UserService(new UserRepositoryMock()));
    } 
}
