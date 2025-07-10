package com.example.ecsite.config;

import com.example.ecsite.jwt.JwtProperties;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.ECDSASigner;
import com.nimbusds.jose.crypto.ECDSAVerifier;
import com.nimbusds.jose.crypto.Ed25519Signer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtConfig {

    @Bean
    public JWSSigner jwsSigner(JwtProperties jwtprops) throws JOSEException {
        return new ECDSASigner(jwtprops.privateKey());
    }

    @Bean
    public JWSVerifier jwsVerifier(JwtProperties jwtprops) throws JOSEException {
        return new ECDSAVerifier(jwtprops.publicKey());
    }
}
