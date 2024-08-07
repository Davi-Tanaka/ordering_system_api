package com.app.interfaces.interceptor;

import com.app.application.service.jwt.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

@NoArgsConstructor
@RequiredArgsConstructor
public class AuthorizationInterceptor implements HandlerInterceptor {
    @NonNull
    @Autowired
    private JwtService jwtService;
    
    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler
    ) throws Exception {
        try {
            String authorizationHeaderString = request.getHeader("authorization").trim().split(" ")[1];
            boolean isAcessTokenValid = jwtService.isAcessTokenValid(authorizationHeaderString);
            
            if (
                authorizationHeaderString == null   || 
                authorizationHeaderString.isEmpty() || 
                authorizationHeaderString.isBlank() ||
                !isAcessTokenValid
            ) {
                response.setStatus(401);
                return false;
            } else {
                return true;
            }
        } catch (Exception err) {
            response.setStatus(401);
            return false;            
        }    
    }
}
