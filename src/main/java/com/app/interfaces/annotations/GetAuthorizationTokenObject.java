package com.app.interfaces.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import com.app.interfaces.dto.jwt.JwtAcessTokenPayloadDto;
        
/**
 * This annotations return a {@link JwtAcessTokenPayloadDto} class from JWT token string inside authorization header.
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface GetAuthorizationTokenObject {}