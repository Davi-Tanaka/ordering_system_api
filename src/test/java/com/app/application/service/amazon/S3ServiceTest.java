package com.app.application.service.amazon;

import com.app.config.amazon.S3Config;
import java.io.ByteArrayInputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
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
    
    private String user_image_key = "user_image_lorem_ipsulum.txt";
    private String product_image_key = "product_image_lorem_ipsulum.txt";
    private String str = "lorem ipsulum loro lala";
    
    @Test
    @Order(1)
    public void must_save_object() throws Exception {
        s3Service.upload(
            s3Config.getUserImageBucketName(), 
            user_image_key, 
            new ByteArrayInputStream(str.getBytes(StandardCharsets.UTF_8)),
            "text/plain"
        );
        
        s3Service.upload(
            s3Config.getProductImageBucketName(), 
            product_image_key,
            new ByteArrayInputStream(str.getBytes(StandardCharsets.UTF_8)),
            "text/plain"
        );
    }
    
    @Test
    @Order(2)

    public void must_return_the_requested_bucket_URL() throws Exception {
        URL userImageUrl = s3Service.getUrl(s3Config.getUserImageBucketName(), user_image_key);
        URL productImageUrl = s3Service.getUrl(s3Config.getProductImageBucketName(), product_image_key);
        
        Assertions.assertTrue(userImageUrl.toString().length() > 0);
        Assertions.assertTrue(productImageUrl.toString().length() > 0);
    }
    
    @Test
    @Order(3)
    public void must_delete() throws Exception {
        s3Service.delete(s3Config.getProductImageBucketName(), product_image_key);
        s3Service.delete(s3Config.getUserImageBucketName(), user_image_key);

    }
}
