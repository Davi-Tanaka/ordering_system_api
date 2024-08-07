package com.app.interfaces.dto.jwt;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

@JsonTypeInfo(use = Id.NONE)
@JsonSubTypes({ 
    @Type(JwtClaims.class) 
})
public interface IJwtClaims {}
