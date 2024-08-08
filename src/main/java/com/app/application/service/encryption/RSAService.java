package com.app.application.service.encryption;

import java.nio.charset.StandardCharsets;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Base64;
import javax.crypto.Cipher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class RSAService {    
    private String CIPHER_DEFAULT_INSTANCE = "RSA/ECB/PKCS1Padding";
        
    @Autowired
    @Qualifier("rsa_private_key")
    private RSAPrivateKey privateKey;
    
    @Autowired
    @Qualifier("rsa_public_key")
    private RSAPublicKey publicKey;
    
    public String encrypt(String message) throws Exception {
        return encrypt(message, publicKey);
    }
            
    public String encrypt(String message, RSAPublicKey key) throws Exception {
        Cipher encrypt = Cipher.getInstance(CIPHER_DEFAULT_INSTANCE);
        encrypt.init(Cipher.ENCRYPT_MODE, key);
        
        byte[] encryptedMessage = encrypt.doFinal(message.getBytes(StandardCharsets.UTF_8));
        return new String(encode(encryptedMessage));
    }
    
    public String decrypt(String encryptedMessage) throws Exception {
        return decrypt(encryptedMessage, privateKey);
    }
    
    public String decrypt(String encryptedMessage, RSAPrivateKey key) throws Exception {
        Cipher decrypt = Cipher.getInstance(CIPHER_DEFAULT_INSTANCE);
        decrypt.init(Cipher.DECRYPT_MODE, key);
        
        byte[] decodedMsg = decode(encryptedMessage);        
        return new String(decrypt.doFinal(decodedMsg));
    }

    private byte[] encode(byte[] d) { return Base64.getEncoder().encode(d); }
    private byte[] decode(String d) { return Base64.getDecoder().decode(d.getBytes()); }    
}
