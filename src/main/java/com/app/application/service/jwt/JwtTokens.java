package com.app.application.service.jwt;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class JwtTokens {
    @JsonProperty("acessToken")
    private String acessToken;
    
    @JsonProperty("refreshToken")
    private String refreshToken;    
}
