package share.mock.service;

import com.app.application.service.encryption.RSAService;

public class RSAServiceMock {
    public static RSAService getMock() {
        RSAService rsaService = new RSAService();
        rsaService.setPrivateKeyPath("/keys/rsa/private_key.pem");
        rsaService.setPublicKeyPath("/keys/rsa/public_key.pem");
        
        return rsaService;
    }
}
