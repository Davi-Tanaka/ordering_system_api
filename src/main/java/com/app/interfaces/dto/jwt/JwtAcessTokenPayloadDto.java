package com.app.interfaces.dto.jwt;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigInteger;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder
@Jacksonized
@NoArgsConstructor
@RequiredArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class JwtAcessTokenPayloadDto implements IJwtClaims {    
    @NonNull
    @JsonProperty("id")
    private Long id;

    @NonNull
    @JsonProperty("name")
    private String name;

    @NonNull
    @JsonProperty("email")
    private String email;
}
