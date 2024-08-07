package com.app.application.service.encryption;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import share.mock.service.RSAServiceMock;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RSAServiceTest {

    private static String data_to_encrypt = "Hello World. Lorem Ipsulum Go Go Ha Ha !";
    private static String encrypted_data;
    private static RSAService rsaService = RSAServiceMock.getMock();
        
    @Test
    @Order(1)
    public void must_encrypt() throws Exception {
        encrypted_data = rsaService.encrypt(data_to_encrypt);                        
        assertFalse(encrypted_data.isEmpty());
    }
    
    @Test
    @Order(2)
    public void must_decrypt() throws Exception {
        String decrypted_data = rsaService.decrypt(encrypted_data);
        assertTrue(data_to_encrypt.equals(decrypted_data));
    }
}