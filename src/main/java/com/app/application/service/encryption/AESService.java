package com.app.application.service.encryption;

import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.KeySpec;
import java.util.Base64;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AESService {
    @Value("${encryption.aes.key}")
    private String secretKeyPath;
    private Cipher cipher;

    public void setSecretKeyPath(String secretKeyPath) {
        this.secretKeyPath = secretKeyPath;
    }
    
    public void AESService() throws Exception, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException {
        this.cipher = Cipher.getInstance("AES");
        this.cipher.init(Cipher.DECRYPT_MODE, getSecretKey());
    }
    
    public String encrypt(String data) throws Exception, IllegalBlockSizeException, BadPaddingException {
        byte[] cipherText = cipher.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(cipherText);
    }
    
    public String decrypt(String data) throws Exception, IllegalBlockSizeException, BadPaddingException {
        byte[] cipherText = cipher.doFinal(data.getBytes());
        return new String(Base64.getDecoder().decode(cipherText));
    }
        
    public SecretKey getSecretKey() throws Exception {
        byte[] salt = new byte[0];

        InputStream inputStreamPrivateKey = AESService.class.getResourceAsStream(secretKeyPath);
        String privateKeyString = new String(inputStreamPrivateKey.readAllBytes())
                .replaceAll(System.lineSeparator(), "");
        
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        KeySpec spec = new PBEKeySpec(privateKeyString.toCharArray(), salt, 65536, 256);
        return new SecretKeySpec(factory.generateSecret(spec).getEncoded(), "AES");
    }
}