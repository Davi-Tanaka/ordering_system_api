package share.mock.service;

import com.app.application.service.jwt.JwtService;
import share.mock.repository.UserRepositoryMock;

public class JwtServiceMock {

    public static JwtService getMock() {
        JwtService jwtService = new JwtService();
        
        jwtService.setUserRepository(new UserRepositoryMock());
        jwtService.setRsaService(RSAServiceMock.getMock());

        return jwtService;
    } 
}
