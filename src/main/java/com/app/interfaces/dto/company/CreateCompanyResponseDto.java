package com.app.interfaces.dto.company;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CreateCompanyResponseDto(
    @JsonProperty("id")
    Long id,
    
    @JsonProperty("user_id")
    Long user_id,
    
    @JsonProperty("name")
    Long name
) {}
