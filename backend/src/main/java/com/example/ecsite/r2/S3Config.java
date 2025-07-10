package com.example.ecsite.r2;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "r2")
@Getter
//@Setter
public class S3Config {
    private String accessKey;
    private String secretKey;
    private String endpoint;
}
