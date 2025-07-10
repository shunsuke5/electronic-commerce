package com.example.ecsite.jwt;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.Ed25519Signer;
import com.nimbusds.jose.crypto.Ed25519Verifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TokenSigner implements InitializingBean {
    private final JWSSigner signer;
    private final JWSVerifier verifier;

    public SignedJWT sign(JWTClaimsSet claimsSet) {
        JWSHeader header = new JWSHeader.Builder(JWSAlgorithm.ES256)
                .type(JOSEObjectType.JWT)
                .build();
        SignedJWT signedJWT = new SignedJWT(header, claimsSet);

        try {
            signedJWT.sign(this.signer);
        } catch (JOSEException e) {
            throw new IllegalStateException(e);
        }

        return signedJWT;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        // Validate the key-pair
        JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                .subject("test")
                .build();
        SignedJWT signedJWT = sign(claimsSet);

        if (!signedJWT.verify(this.verifier)) {
            throw new IllegalStateException("The pair of public key and private key is wrong.");
        }
    }
}
