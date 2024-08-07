package com.app.interfaces.dto.jwt;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigInteger;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.extern.jackson.Jacksonized;

@Data
@Jacksonized
@Builder
public class JwtClaims {
    @JsonProperty(value = "jti", access = JsonProperty.Access.WRITE_ONLY)
    @NonNull
    private String jti;
    
    @NonNull
    @JsonProperty(value = "iss", access = JsonProperty.Access.WRITE_ONLY)
    private String iss;
    
    @NonNull
    @JsonProperty(value = "sub", access = JsonProperty.Access.WRITE_ONLY)
    private String sub;
    
    @NonNull
    @JsonProperty(value = "iat", access = JsonProperty.Access.WRITE_ONLY)
    private BigInteger iat;
    
    @NonNull
    @JsonProperty(value = "exp", access = JsonProperty.Access.WRITE_ONLY)
    private BigInteger exp;
}