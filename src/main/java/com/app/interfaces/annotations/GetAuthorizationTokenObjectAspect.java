package com.app.interfaces.annotations;

import com.app.application.service.jwt.JwtService;
import com.app.interfaces.dto.jwt.JwtAcessTokenPayloadDto;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;

@Aspect
public class GetAuthorizationTokenObjectAspect {
    @Autowired
    private JwtService jwtService;
    
    @Around("@annotaion(GetAuthorizationTokenObject)")
    public JwtAcessTokenPayloadDto getData(
            @RequestHeader Map<String, String> headers
    ) throws Throwable {
        String authStringToken    = headers.get("authorization");
        boolean isAcessTokenValid = jwtService.isAcessTokenValid(authStringToken);
        
        if(isAcessTokenValid) {
            try {
                new ObjectMapper().readValue(
                    jwtService.decode(authStringToken),
                    JwtAcessTokenPayloadDto.class
                );
            } catch(Exception err) {
                err.printStackTrace();
                return null;
            }
        }
        
        return null;
    }
}