package com.example.ecsite.r2;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "r2")
public record S3Config(String accessKey, String secretKey, String endpoint) {
}
