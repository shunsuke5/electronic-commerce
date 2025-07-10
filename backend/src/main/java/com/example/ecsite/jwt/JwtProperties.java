package com.example.ecsite.jwt;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;

@ConfigurationProperties(prefix = "jwt")
public record JwtProperties(ECPrivateKey privateKey, ECPublicKey publicKey) {

}
