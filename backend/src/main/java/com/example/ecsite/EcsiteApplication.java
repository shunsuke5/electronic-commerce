package com.example.ecsite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class EcsiteApplication {

    public static void main(String[] args) {
        SpringApplication.run(EcsiteApplication.class, args);
    }

}
