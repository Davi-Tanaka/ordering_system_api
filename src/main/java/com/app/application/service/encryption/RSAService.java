package com.app.application.service.encryption;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import javax.crypto.Cipher;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RSAService {    
    private String CIPHER_DEFAULT_INSTANCE = "RSA/ECB/PKCS1Padding";
    
    @Value("${encryption.rsa.key.private}")
    private String privateKeyPath;
    
    @Value("${encryption.rsa.key.public}")
    private String publicKeyPath;

    public RSAService() {}

    public RSAService(String privateKeyPath, String publicKeyPath) {
        this.privateKeyPath = privateKeyPath;
        this.publicKeyPath = publicKeyPath;
    }
   
    public void setPrivateKeyPath(String privateKeyPath) {
        this.privateKeyPath = privateKeyPath;
    }

    public void setPublicKeyPath(String publicKeyPath) {
        this.publicKeyPath = publicKeyPath;
    }
    
    public String encrypt(String message) throws Exception {
        return encrypt(message, getPublicKey());
    }
            
    public String encrypt(String message, RSAPublicKey key) throws Exception {
        Cipher encrypt = Cipher.getInstance(CIPHER_DEFAULT_INSTANCE);
        encrypt.init(Cipher.ENCRYPT_MODE, key);
        
        byte[] encryptedMessage = encrypt.doFinal(message.getBytes(StandardCharsets.UTF_8));
        return new String(encode(encryptedMessage));
    }
    
    public String decrypt(String encryptedMessage) throws Exception {
        return decrypt(encryptedMessage, getPrivateKey());
    }
    
    public String decrypt(String encryptedMessage, RSAPrivateKey key) throws Exception {
        Cipher decrypt = Cipher.getInstance(CIPHER_DEFAULT_INSTANCE);
        decrypt.init(Cipher.DECRYPT_MODE, key);
        
        byte[] decodedMsg = decode(encryptedMessage);        
        return new String(decrypt.doFinal(decodedMsg));
    }

    public RSAPrivateKey getPrivateKey() throws Exception {
        InputStream inputStreamPrivateKey = RSAService.class.getResourceAsStream(privateKeyPath);

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
    
    public RSAPublicKey getPublicKey() throws Exception {        
        InputStream inputStreamPublicKey = RSAService.class.getResourceAsStream(publicKeyPath);
        
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
    
    private byte[] encode(byte[] d) { return Base64.getEncoder().encode(d); }
    private byte[] decode(String d) { return Base64.getDecoder().decode(d.getBytes()); }    
}
