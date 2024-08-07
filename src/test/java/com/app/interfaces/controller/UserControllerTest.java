package com.app.interfaces.controller;

import com.app.application.service.UserService;
import com.app.domain.database.entity.user.UserEntity;
import com.app.interfaces.dto.auth.UserAuthenticationDto;
import com.app.interfaces.dto.user.CreateUserRequestDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import jakarta.transaction.Transactional;
import java.math.BigDecimal;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@WebAppConfiguration
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Transactional
public class UserControllerTest {
    
    @Autowired
    UserService userService;

    @Autowired
    private MockMvc mockMvc;
    
    @Autowired
    private static CreateUserRequestDto validCreateUserDto = new CreateUserRequestDto(
            "Valid User DTO name",
            "380.559.110-10",
            "validuserdto@fakemail.com",
            "pswd123",
            new BigDecimal(1450.50)
    );
    
    private static UserEntity createdUserEntity;
    
    @Autowired
    private MockMvc mvc;
    private ObjectWriter objWriter = new ObjectMapper()
            .writer()
            .withDefaultPrettyPrinter();

    
    @Test
    @Order(1)
    @DisplayName("[/user/signup] - When receives dto malformed it should response with 401 status")
    void when_receive_dto_malformed__then_response_with_401_status() throws Exception {
        CreateUserRequestDto malformed_dto = CreateUserRequestDto.builder().name("Malformde DTO").build();

        RequestBuilder request = MockMvcRequestBuilders.post("/user/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objWriter.writeValueAsString(malformed_dto));
        
        ResultActions result = mvc.perform(request);
        result.andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    @Order(2)
    @DisplayName("[/user/signup] - When receives valid dto it should response with 201 status")
    void when_receive_valid_dto__then_response_with_201_status() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.post("/user/signup")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objWriter.writeValueAsString(validCreateUserDto));

        ResultActions result = mvc.perform(request);
        result.andExpect(MockMvcResultMatchers.status().isCreated());
    }
    
    @Test
    @Order(3)
    @DisplayName("[user/login] - When receives authorized to login it response with status 200 and with \"acessToken\" and \"refreshToken\".")
    public void when_receives_authorized_to_login__it_response_with_acess_token_and_refresh_token() throws Exception {
        UserAuthenticationDto authenticationDto = new UserAuthenticationDto(
            validCreateUserDto.getEmail(),
            validCreateUserDto.getPassword()
        );
        
        userService.create(validCreateUserDto);

        RequestBuilder authorizationRequest = MockMvcRequestBuilders.post("/user/login")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objWriter.writeValueAsString(authenticationDto));

        ResultActions authorizationResult = mvc.perform(authorizationRequest);
                
        authorizationResult
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("acessToken").exists())
            .andExpect(MockMvcResultMatchers.jsonPath("refreshToken").exists());
    }

    @Test
    @Order(4)
    @DisplayName("[/user/login] - When receives invalid dto it should response with 401 status")
    public void when_reives_invalid_dto__it_should_response_with_401_status() throws Exception {
        UserAuthenticationDto invalidAuthDto__both_fields_null = new UserAuthenticationDto(null, null);
        UserAuthenticationDto invalidAuthDto__just_email_is_null = new UserAuthenticationDto(null, "pswd");

        RequestBuilder request_one = MockMvcRequestBuilders.post("/user/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objWriter.writeValueAsString(invalidAuthDto__both_fields_null));

        RequestBuilder request_two = MockMvcRequestBuilders.post("/user/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objWriter.writeValueAsString(invalidAuthDto__just_email_is_null));

        ResultActions result_one = mvc.perform(request_one);
        ResultActions result_two = mvc.perform(request_two);

        result_one.andExpect(MockMvcResultMatchers.status().isUnauthorized());
        result_two.andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }
}
