package com.app.application.service.amazon;

import com.app.config.amazon.S3Config;
import java.io.InputStream;
import java.net.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.GetUrlRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

@Service
public class S3Service {    
    @Autowired
    private S3Config s3;
        
    public void upload(
        String bucketName,
        String key, 
        InputStream file, 
        String contentType
    ) throws Exception {
        S3Client client = s3.getS3Client();
        
        PutObjectRequest putRequest = PutObjectRequest.builder()
            .bucket(bucketName)
            .key(key)
            .contentType(contentType)
            .build();
        
        RequestBody requestBody = RequestBody.fromInputStream(file, file.available());
        client.putObject(putRequest, requestBody);
        
        client.close();
    }
    
    public void delete(String bucketName, String key) throws Exception {
        S3Client client = s3.getS3Client();
        
        DeleteObjectRequest deleteRequest = DeleteObjectRequest.builder()
            .bucket(bucketName)
            .key(key)
            .build();
        
        client.deleteObject(deleteRequest);
        client.close();
    };
    
    public URL getUrl(String bucketName, String key) throws Exception {
        S3Client client = s3.getS3Client();

        GetUrlRequest getUrlRequest = GetUrlRequest.builder()
            .bucket(bucketName)
            .key(key)
            .build();
        
        URL url = client.utilities().getUrl(getUrlRequest);
        client.close();
        
        return url;
    }
}