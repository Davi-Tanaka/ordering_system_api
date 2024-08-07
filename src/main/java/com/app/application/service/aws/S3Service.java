package com.app.application.service.aws;

import java.net.URL;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import software.amazon.awssdk.awscore.exception.AwsServiceException;
import software.amazon.awssdk.core.exception.SdkClientException;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetUrlRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;

/**
 *
 * @author davi
 */
@NoArgsConstructor
@AllArgsConstructor

//@Service
public class S3Service {
 //   @Value("${aws.s3.region}")
    private String REGION;
    
   // @Value("${aws.s3.bucker_user_image}")
    public static String USER_IMAGE_BUCKET;

    //@Value("${aws.s3.bucket_product_image}")
    public static String PRODUCT_IMAGE_BUCKET;

    private S3Client s3 = S3Client.builder()
            .region(Region.of(REGION))
            .build();
    
    /**
     * Get URL from bucket object.
     * @param bucketName
     * @param key
     * @return URL 
     */
    public URL getURL(String bucketName, String key) {
        try {
            GetUrlRequest request = GetUrlRequest.builder()
                    .bucket(bucketName)
                    .key(key)
                    .build();

            URL url = s3.utilities().getUrl(request);
            return url;
        } catch (S3Exception e) {
            System.err.println(e.awsErrorDetails().errorMessage());
            return null;
        }
    }
    
    /**
     * @param bucketName
     * @param contentType
     * @param key
     * @param content
     * @return boolean - Returns true if success, else returns false.
     * @throws AwsServiceException
     * @throws SdkClientException
     * @throws S3Exception 
     */
    public boolean upload(
            String bucketName,
            String contentType, 
            String key,
            byte[] content
    ) throws AwsServiceException, SdkClientException, S3Exception {
        try {
            PutObjectRequest request = PutObjectRequest.builder()
                .bucket(bucketName)
                .contentType(contentType)
                .key(key)
                .build();

            s3.putObject(request, RequestBody.fromBytes(content));
            
            return true;
        } catch (Exception err) {
            System.err.print(err);
            return false;
        }
    }
}
