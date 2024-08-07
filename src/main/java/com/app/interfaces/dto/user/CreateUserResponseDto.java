package com.app.interfaces.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.sql.Timestamp;

@Schema(name = "Create User Response Dto.")
public record CreateUserResponseDto(
    @JsonProperty("id")
    Long id,

    @JsonProperty("name")
    String name,

    @JsonProperty("createdAt")
    Timestamp createdAt,

    @JsonProperty("updatedAt")
    Timestamp updatedAt) 
{}
