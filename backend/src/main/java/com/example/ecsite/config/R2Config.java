package com.example.ecsite.config;

import com.example.ecsite.r2.CloudflareR2Client;
import com.example.ecsite.r2.S3Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.S3Configuration;

import java.net.URI;

@Configuration
public class R2Config {

    @Bean
    public CloudflareR2Client cloudflareR2Client(S3Client s3Client) {
        return new CloudflareR2Client(s3Client);
    }

    @Bean
    public S3Client S3Client(S3Config config) {
        AwsBasicCredentials credentials = AwsBasicCredentials.create(
                config.getAccessKey(),
                config.getSecretKey()
        );

        S3Configuration serviceConfiguration = S3Configuration.builder()
                .pathStyleAccessEnabled(true)
                .build();

        return S3Client.builder()
                .endpointOverride(URI.create(config.getEndpoint()))
                .credentialsProvider(StaticCredentialsProvider.create(credentials))
                .region(Region.of("auto"))
                .serviceConfiguration(serviceConfiguration)
                .build();
    }

    @Bean
    public S3Config s3Config() {
        return new S3Config();
    }
}
