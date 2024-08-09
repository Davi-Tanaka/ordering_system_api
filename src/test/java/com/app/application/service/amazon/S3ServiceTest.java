package com.app.application.service.amazon;

import com.app.config.amazon.S3Config;
import java.net.URL;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class S3ServiceTest {
    @Autowired
    private S3Service s3Service;
    
    @Autowired
    private S3Config s3Config;
    
    private String str = "lorem ipsulum loro lala";
    
    @Test
    public void can_save_object() throws Exception {
        URL insertedUserObjectUrl = s3Service.upload(
            s3Config.getUserImageBucket(), 
            "1", 
            new String(str).getBytes(), 
            "text/plain"
        );
        
        URL insertedProductObjectUrl = s3Service.upload(
            s3Config.getProductImageBucket(), 
            "1", 
            new String(str).getBytes(), 
            "text/plain"
        );
        
        Assertions.assertFalse(insertedUserObjectUrl.toExternalForm().isBlank());
        Assertions.assertFalse(insertedProductObjectUrl.toExternalForm().isBlank());
    }
    
//    @Test
//    public void can_delete() {
//        
//    }
}
