package com.app.interfaces.controller.user;

import com.app.application.service.user.UserService;
import com.app.application.service.auth.AuthService;
import com.app.application.service.jwt.JwtService;
import com.app.application.service.jwt.JwtTokens;
import com.app.application.service.model.IResponseModel;
import com.app.domain.database.entity.user.UserEntity;
import com.app.interfaces.annotations.GetAuthorizationTokenObject;
import com.app.interfaces.dto.auth.UserAuthenticationDto;
import com.app.interfaces.dto.jwt.JwtAcessTokenPayloadDto;
import com.app.interfaces.dto.user.CreateUserRequestDto;
import com.app.interfaces.dto.user.CreateUserResponseDto;
import jakarta.validation.Valid;
import java.sql.SQLException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Setter
@Getter
@NoArgsConstructor
@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController implements IUserController {
    @NonNull
    @Autowired
    private UserService userService;
    
    @NonNull
    @Autowired
    private AuthService authService;

    @NonNull
    @Autowired
    private JwtService jwtService;
        
    @GetMapping("/me")
    @Override
    public ResponseEntity getAuthenticatedUserData() {
        return ResponseEntity.ok("");
    }
        
    @PostMapping("/signup")
    @Override
    public ResponseEntity signUp(@Valid @RequestBody CreateUserRequestDto body) {
        
        IResponseModel<Boolean, Error> userCanSignup = userService.canSignup(
            new ModelMapper().map(body, UserEntity.class)
        );

        if(userCanSignup.hasError()) {               
            return ResponseEntity.status(HttpStatus.CONFLICT).body(
                userCanSignup.getError().getMessage()
            );
        }

        try {
            CreateUserResponseDto response = userService.create(body);
            
            return ResponseEntity.status(201).body(response);
        } catch (SQLException err) {
            err.printStackTrace();
            
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/login")
    @Override
    public ResponseEntity login(@Valid @RequestBody UserAuthenticationDto body) {
        try {
            UserEntity authenticatedUser = authService.authenticate(
                body.getEmail(), 
                body.getPassword()
            );
            
            JwtTokens tokens = jwtService.generateTokens(authenticatedUser);
            
            return ResponseEntity.ok(tokens);
            
        } catch (Exception err) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();            
        } 
    }
    
    @PostMapping("/delete")
    @Override
    public ResponseEntity delete(
            @GetAuthorizationTokenObject JwtAcessTokenPayloadDto tokenObject
    ) {
        this.userService.delete(tokenObject.getId());
        return new ResponseEntity(HttpStatus.OK);
    }
}