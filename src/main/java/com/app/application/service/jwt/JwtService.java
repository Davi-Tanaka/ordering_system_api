package com.app.application.service.jwt;

import com.app.application.service.encryption.RSAService;
import com.app.domain.database.entity.user.UserEntity;
import com.app.domain.database.repository.UserRepository;
import com.app.interfaces.dto.jwt.JwtAcessTokenPayloadDto;
import com.app.interfaces.dto.jwt.JwtRefreshTokenPayloadDto;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Base64;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class JwtService {
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    @Qualifier("rsa_private_key")
    private RSAPrivateKey rsaPrivateKey;
    
    @Autowired
    @Qualifier("rsa_public_key")
    private RSAPublicKey rsaPublicKey;
    
    /**
     * The algorithm that JwtService will use to sign tokens.
     */
    private Algorithm algorithm;

    /**
     * The JWT verifier that will validate the token signature.
     */
    private JWTVerifier verifier;

    /**
     * Duration in miliseconds that acess tokená will expires.
     */
    private long acessTokenExpiresAt = 900000L; // 15 minutes

    /**
     * Duration in miliseconds that refresh token will expires.
     */
    private long refreshTokenExpiresAt = 86400000L; // 1 day
    
    /**
     * Used for transform object to json.
     */
    private ObjectWriter objectWriter = new ObjectMapper()
        .writer()
        .withDefaultPrettyPrinter();

    public JwtService()  {}

    @PostConstruct
    public void injectDeps() {
        try {
            this.algorithm = Algorithm.RSA256(rsaPublicKey, rsaPrivateKey);
            this.verifier = JWT.require(this.algorithm).build();
        } catch (Exception err) {
            err.printStackTrace();
        }
    }
    
    public JwtService setAlgorithm(Algorithm algorithm) {
        this.algorithm = algorithm;        
        this.verifier = JWT.require(this.algorithm).build();
        
        return this;
    }
    
    public long getAcessTokenExpiresAt() {
        return acessTokenExpiresAt;
    }

    public JwtService setAcessTokenExpiresAt(long acessTokenExpiresAt) {
        this.acessTokenExpiresAt = acessTokenExpiresAt;
        return this;
    }

    public long getRefreshTokenExpiresAt() {
        return refreshTokenExpiresAt;
    }

    public JwtService setRefreshTokenExpiresAt(long refreshTokenExpiresAt) {
        this.refreshTokenExpiresAt = refreshTokenExpiresAt;
        return this;
    }

    public JwtService setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
        return this;
    }
    
    /**
     * Generate access and refresh token from user data.
     *
     * @param user
     * @return JwtTokens
     */
    public JwtTokens generateTokens(UserEntity user) throws Exception {
        JwtAcessTokenPayloadDto acessTokenPayload = new JwtAcessTokenPayloadDto(
            user.getId(),
            user.getName(),
            user.getEmail()
        );

        JwtRefreshTokenPayloadDto refreshTokenPayload = new JwtRefreshTokenPayloadDto(
            user.getId()
        );

        String JWTAcessToken = generateAcessToken(acessTokenPayload);
        String JWTRefreshToken = generateRefreshToken(refreshTokenPayload);  
        
        JwtTokens jwtTokens = new JwtTokens(JWTAcessToken, JWTRefreshToken);

        return jwtTokens;
    }
    
    public JwtTokens generateTokens(UserEntity user, long acessTokenExpiration, long refreshTokenExpires) throws Exception {
        setAcessTokenExpiresAt(acessTokenExpiration);
        setRefreshTokenExpiresAt(refreshTokenExpires);

        JwtTokens tokens = generateTokens(user);
        
        setAcessTokenExpiresAt(acessTokenExpiresAt);
        setRefreshTokenExpiresAt(refreshTokenExpiresAt);
        
        return tokens;
    }
    
    public String generateAcessToken(JwtAcessTokenPayloadDto payload) 
            throws JWTCreationException, 
            IllegalArgumentException, 
            JsonProcessingException 
    {
        
        String jsonPayload = objectWriter.writeValueAsString(payload);

        String userJWTAcessToken = JWT.create()
            .withIssuer(payload.getName())
            .withSubject("acess token")
            .withIssuedAt(new Date())
            .withExpiresAt(new Date(new Date().getTime() + acessTokenExpiresAt))
            .withJWTId(UUID.randomUUID().toString())
            .withPayload(jsonPayload)
            .sign(this.algorithm);
                
        return userJWTAcessToken;
    }
   
    public String generateRefreshToken(JwtRefreshTokenPayloadDto payload) throws 
            JWTCreationException, 
            IllegalArgumentException,
            JsonProcessingException
        {    
            
        String jsonPayload = objectWriter.writeValueAsString(payload);
        String userJWTRefreshToken = JWT.create()
            .withSubject("refresh token")
            .withIssuedAt(new Date())
            .withExpiresAt(new Date(System.currentTimeMillis() + refreshTokenExpiresAt))
            .withPayload(jsonPayload)
            .withJWTId(UUID.randomUUID().toString())
            .sign(this.algorithm);

        return userJWTRefreshToken;
    }

    public boolean isAcessTokenValid(String acessToken) {
        try {
            DecodedJWT decodedJWT = verifier.verify(acessToken);
            return true;
        } catch (JWTVerificationException e) {
            return false;
        }
    }

    public boolean isRefreshTokenValid(String refreshToken) {
        try {
            DecodedJWT decodedJWT = verifier.verify(refreshToken);
            return true;
        } catch (JWTVerificationException e) {
            return false;
        }
    }

    public JwtTokens refreshTokens(JwtTokens tokens) throws Exception, JWTVerificationException {
        boolean acessTokenIsValid = isAcessTokenValid(tokens.getAcessToken());
        boolean refreshTokenIsValid = isAcessTokenValid(tokens.getRefreshToken());
        
        if(acessTokenIsValid) {
            throw new JWTVerificationException("Acess Token still valid. Refresh Token must receive a acess token invalided by time.");
        }
        
        if(!refreshTokenIsValid) {
            throw new JWTVerificationException("The Refresh token provided is not valid -> " + tokens.getRefreshToken());
        }
        
        ObjectMapper objectMapper = new ObjectMapper();        
        String acessTokenPayloadString = JWT.decode(tokens.getAcessToken()).getPayload();
        String refreshTokenPayloadString = JWT.decode(tokens.getRefreshToken()).getPayload();

        String decodedAcessToken = decode(acessTokenPayloadString);
        String decodedRefreshToken = decode(refreshTokenPayloadString);
        
        JwtAcessTokenPayloadDto acessTokenDecoded = objectMapper.readValue(
            decodedAcessToken,
            JwtAcessTokenPayloadDto.class
        );

        JwtRefreshTokenPayloadDto refreshTokenDecoded = objectMapper.readValue(
            decodedRefreshToken,
            JwtRefreshTokenPayloadDto.class
        );

        Optional<UserEntity> user = userRepository.findById(refreshTokenDecoded.getId());

        if (user.isEmpty()) {
            throw new Exception("Invalid refresh token.");
        }

        String refreshedAcessToken = generateAcessToken(acessTokenDecoded);
        String refreshedRefreshToken = generateRefreshToken(refreshTokenDecoded);

        JwtTokens refreshedTokens = new JwtTokens(refreshedAcessToken, refreshedRefreshToken);

        return refreshedTokens;  
    }
    
    public String decode(String token) {
        String decodedPayload = new String(
                Base64.getDecoder().decode(token)
        );
        
        return decodedPayload;
    }
}
