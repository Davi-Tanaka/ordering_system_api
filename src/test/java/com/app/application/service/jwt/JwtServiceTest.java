package com.app.application.service.jwt;

import com.app.domain.database.entity.user.UserEntity;
import com.app.domain.database.repository.UserRepository;
import com.app.interfaces.dto.jwt.JwtAcessTokenPayloadDto;
import com.app.interfaces.dto.jwt.JwtRefreshTokenPayloadDto;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import share.mock.entity.UserEntityMock;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Transactional
public class JwtServiceTest {    
    @Autowired
    private JwtService jwtService;
        
    @Autowired
    private UserRepository userRepository;
    
    private final String idiotStr = "it_will_make_jwt_invalid";
    private static UserEntity mocked_user;
    
    @BeforeAll
    public void init() throws Exception {
        mocked_user = userRepository.save(UserEntityMock.getValidMock());
    }
    
    @AfterAll
    public void clear() throws Exception {
        userRepository.deleteById(mocked_user.getId());
    }

    @Test
    @Order(1)
    public void can_generate_acess_token() throws Exception {
        JwtAcessTokenPayloadDto payload = new JwtAcessTokenPayloadDto(
            mocked_user.getId(),
            mocked_user.getName(),
            mocked_user.getEmail()
        );        
        
        String acesssToken = jwtService.generateAcessToken(payload);               
        Assertions.assertTrue(acesssToken.length() >  0);
    }

    @Test
    @Order(2)
    public void can_generate_refresh_token() throws Exception {
        JwtRefreshTokenPayloadDto payload = new JwtRefreshTokenPayloadDto(mocked_user.getId()); 
        
        String refreshToken = jwtService.generateRefreshToken(payload);        
        Assertions.assertTrue(refreshToken.length() >  0);
    }
    
    @Test
    @Order(3)
    public void can_generate_tokens_from_user_data() throws Exception {
        JwtTokens tokens = jwtService.generateTokens(mocked_user);

        Assertions.assertTrue(tokens.getAcessToken().length() > 0);
        Assertions.assertTrue(tokens.getRefreshToken().length() > 0);
    }
    
    @Test
    @Order(4)
    public void can_identify_invalid_tokens() throws Exception {
        JwtTokens tokens = jwtService.generateTokens(mocked_user);
        
        boolean acessTokenIsValid = jwtService.isAcessTokenValid(tokens.getAcessToken() + idiotStr);
        boolean refreshTokenIsValid = jwtService.isRefreshTokenValid(tokens.getRefreshToken() + idiotStr);
        
        Assertions.assertFalse(acessTokenIsValid);
        Assertions.assertFalse(refreshTokenIsValid);
    }

    @Test
    @Order(5)
    public void can_identify_valid_tokens() throws Exception {
        JwtTokens tokens = jwtService.generateTokens(mocked_user, 10000, 10000);
        
        boolean acessTokenIsValid = jwtService.isAcessTokenValid(tokens.getAcessToken());
        boolean refreshTokenIsValid = jwtService.isRefreshTokenValid(tokens.getRefreshToken());
        
        Assertions.assertTrue(acessTokenIsValid);
        Assertions.assertTrue(refreshTokenIsValid);
    }
    
    @Test
    @Order(6)
    public void can_refresh_tokens() throws Exception {              
        JwtTokens tokens = jwtService.generateTokens(mocked_user, 100L, 5000L);   
        Thread.sleep(1000);
                
        JwtTokens newTokens = jwtService.refreshTokens(tokens);
                                
        Assertions.assertFalse(tokens.getAcessToken().equals(newTokens.getAcessToken()));
        Assertions.assertFalse(tokens.getRefreshToken().equals(newTokens.getRefreshToken()));           
    }
    
    @Test
    @Order(7)
    public void should_not_refresh_new_tokens_from_valid_acess_token() throws JWTDecodeException, Exception {        
        JwtTokens tokens = jwtService.generateTokens(mocked_user, 10000, 10000);           
        Assertions.assertThrowsExactly(JWTVerificationException.class, () -> jwtService.refreshTokens(tokens));
    }
}
