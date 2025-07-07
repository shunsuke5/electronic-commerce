package com.example.ecsite.r2;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

import java.io.IOException;
import java.util.List;

@Component
public class CloudflareR2Client {
    private final S3Client s3Client;
    private final String BUCKET_NAME;

    public CloudflareR2Client(S3Client s3Client, String bucketName) {
        this.s3Client = s3Client;
        this.BUCKET_NAME = bucketName;
    }

    public List<Bucket> listBuckets() {
        try {
            return s3Client.listBuckets().buckets();
        } catch (S3Exception e) {
            throw new RuntimeException("Failed to list buckets: " + e.getMessage(), e);
        }
    }

    public List<S3Object> listObjects() {
        try {
            ListObjectsV2Request request = ListObjectsV2Request.builder()
                    .bucket(BUCKET_NAME)
                    .build();

            return s3Client.listObjectsV2(request).contents();
        } catch (S3Exception e) {
            throw new RuntimeException("Failed to list objects in bucket " + BUCKET_NAME + ": " + e.getMessage(), e);
        }
    }

    public void putObject(MultipartFile file) {
        PutObjectRequest request = PutObjectRequest.builder()
                .bucket(BUCKET_NAME)
                .key(file.getOriginalFilename())
                .contentType(file.getContentType())
                .build();
        try {
            s3Client.putObject(request,RequestBody.fromBytes(file.getBytes())
            );
        } catch (IOException e) {
            e.getStackTrace();
        }
    }
}
