package com.app.application.service.otp;

import com.app.application.service.user.UserService;
import com.app.interfaces.dto.otp.totp.CreatedTOTPDto;
import com.app.interfaces.dto.user.CreateUserRequestDto;
import com.app.interfaces.dto.user.CreateUserResponseDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import share.mock.entity.UserEntityMock;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Transactional
public class TOTPServiceTest {
    @Autowired
    private TOTPService totpService;
    
    @Autowired
    private UserService userService;
    
    @Test
    @Order(1)
    public void can_generate_totp() throws Exception {
        Assertions.assertTrue(totpService.generate().length() == 8);
    }
    
    @Test
    @Order(2)
    public void can_save_totp() throws Exception {
        CreateUserResponseDto createdUser = userService.create(
            new ModelMapper().map(
                UserEntityMock.getValidMock(),
                CreateUserRequestDto.class
            )
        );
        
        CreatedTOTPDto savedTOTP = totpService.save(createdUser.id());

        Assertions.assertTrue(savedTOTP.getId() != null);
        Assertions.assertTrue(savedTOTP.getId().toString().length() > 0);

        Assertions.assertTrue(savedTOTP.getValue()!= null);
        Assertions.assertTrue(savedTOTP.getValue().length() > 0);
    }
}
