package com.app.interfaces.dto.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserAuthenticationDto {
    @JsonProperty("email")
    private String email;
    
    @JsonProperty("password")
    private String password;    
}