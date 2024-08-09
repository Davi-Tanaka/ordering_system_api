package com.app.config.amazon;

import java.net.URI;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.http.apache.ApacheHttpClient;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@Configuration
public class S3Config {

    @Value("${aws.s3.bucket.region}")
    private String REGION;

    @Value("${aws.s3.bucket.user_image}")
    private String USER_IMAGE_BUCKET;

    @Value("${aws.s3.bucket.product_image}")
    private String PRODUCT_IMAGE_BUCKET;

    @Value("${aws.s3.endpoint}")
    private String ENDPOINT;
    
    @Value("${aws.credentials.secret_key}")
    private String SECRET_ACCESS_KEY;
    
    @Value("${aws.credentials.access_key}")
    private String ACCESS_KEY_ID;

    @Bean("s3_client")
    public S3Client getS3Client() {
        try {
            return S3Client.builder()
                .credentialsProvider(StaticCredentialsProvider.create(
                    AwsBasicCredentials.create(ACCESS_KEY_ID, SECRET_ACCESS_KEY)
                ))
                .httpClient(ApacheHttpClient.create())
                .endpointOverride(new URI(ENDPOINT))
                .region(Region.of(REGION))
                .build();

        } catch (Exception err) {
            err.printStackTrace();
            return null;
        }        
    }
    
    public String getUserImageBucket() {
        return USER_IMAGE_BUCKET;
    }
    
    public String getProductImageBucket() {
        return PRODUCT_IMAGE_BUCKET;
    }
}
