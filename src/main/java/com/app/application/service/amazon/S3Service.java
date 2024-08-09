package com.app.application.service.amazon;

import java.net.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.GetUrlRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

@Service
public class S3Service {    
    @Autowired
    @Qualifier("s3_client")
    private S3Client s3;
    
    public URL upload(
        String bucketName,
        String key, 
        byte[] file, 
        String contentType
    ) throws Exception {
        PutObjectRequest putRequest = PutObjectRequest.builder()
            .bucket(bucketName)
            .key(key)
            .contentType(contentType)
            .build();
        
        RequestBody requestBody = RequestBody.fromBytes(file);
        s3.putObject(putRequest, requestBody);
        
        return getUrl(bucketName, key);
    }
    
    public void delete(String bucketName, String key) throws Exception {
        
        DeleteObjectRequest deleteRequest = DeleteObjectRequest.builder()
            .bucket(bucketName)
            .key(key)
            .build();
        
        s3.deleteObject(deleteRequest);
    };
    
    public URL getUrl(String bucketName, String key) throws Exception {
        GetUrlRequest getUrlRequest = GetUrlRequest.builder().bucket(bucketName).key(key).build();
        return s3.utilities().getUrl(getUrlRequest);
    }
}