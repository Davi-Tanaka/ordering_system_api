package share.mock.service;

import com.app.application.service.UserService;
import com.app.domain.database.repository.UserRepository;
import share.mock.repository.UserRepositoryMock;

public class UserServiceMock {

    public static UserService getMock() {
        UserRepository userRepository = new UserRepositoryMock();
        UserService userService = new UserService(userRepository);
        
        return userService;
    }
}
