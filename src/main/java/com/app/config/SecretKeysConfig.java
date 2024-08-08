package com.app.config;

import com.app.application.service.encryption.RSAService;
import java.io.InputStream;
import java.security.Key;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import javax.crypto.KeyGenerator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SecretKeysConfig {
    @Value("${encryption.rsa.key.public}")
    private String rsa__public_key_path;
    
    @Value("${encryption.rsa.key.private}")
    private String rsa__private_key_path;

    @Value("${encryption.aes.key}")
    private String aes__key;
    
    @Bean("totp_secret_key")
    public Key generateRandomTOTPKey() throws Exception {
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(256);
        
        return keyGen.generateKey();
    }
    
    @Bean("rsa_public_key")
    public RSAPublicKey getRsaPublicKey() throws Exception {        
        InputStream inputStreamPublicKey = RSAService.class.getResourceAsStream(rsa__public_key_path);
        
        byte[] publicKeyPEM = new String(inputStreamPublicKey.readAllBytes())
                .replaceAll(System.lineSeparator(), "")
                .replace("-----BEGIN PUBLIC KEY-----", "")
                .replace("-----END PUBLIC KEY-----", "")
                .getBytes();

        byte[] publicKeyBytes = Base64.getDecoder().decode(publicKeyPEM);

        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(publicKeyBytes);
        PublicKey publicKey = keyFactory.generatePublic(publicKeySpec);

        return (RSAPublicKey) publicKey;
    }
    
    @Bean("rsa_private_key")
    public RSAPrivateKey getRsaPrivateKey() throws Exception {
        InputStream inputStreamPrivateKey = SecretKeysConfig.class.getResourceAsStream(rsa__private_key_path);

        byte[] privateKeyPEM = new String(inputStreamPrivateKey.readAllBytes())
                .replaceAll(System.lineSeparator(), "")
                .replace("-----BEGIN PRIVATE KEY-----", "")
                .replace("-----END PRIVATE KEY-----", "")
                .getBytes();
        
        byte[] privateKeyBytes = Base64.getDecoder().decode(privateKeyPEM);

        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
        PrivateKey privateKey = keyFactory.generatePrivate(privateKeySpec);

        return (RSAPrivateKey) privateKey;
    }
}
