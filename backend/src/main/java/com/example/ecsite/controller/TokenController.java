package com.example.ecsite.controller;

import com.example.ecsite.jwt.TokenSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import static org.springframework.security.oauth2.core.OAuth2AccessToken.TokenType.BEARER;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Map;
import java.util.Set;

@RestController
public class TokenController {

    private final TokenSigner tokenSigner;
    private final AuthenticationManager adminManager;
    private final AuthenticationManager customerManager;
    private final Clock clock;

    public TokenController(TokenSigner signer,
                           @Qualifier("admin") AuthenticationManager adminManager,
                           @Qualifier("customer") AuthenticationManager customerManager,
                           Clock clock) {
        this.tokenSigner = signer;
        this.adminManager = adminManager;
        this.customerManager = customerManager;
        this.clock = clock;
    }

    @PostMapping("/token/admin")
    public Object issueAdminToken(@RequestParam String username, @RequestParam String password, UriComponentsBuilder builder) {
        try {
            Authentication authenticated = adminManager.authenticate(UsernamePasswordAuthenticationToken.unauthenticated(username, password));
            UserDetails userDetails = (UserDetails) authenticated.getPrincipal();
            String issuer = builder.path("").build().toString();
            Instant issuedAt = Instant.now(this.clock);
            Instant expiresAt = issuedAt.plus(1, ChronoUnit.MINUTES);
            Set<String> scope = Set.of("role:admin");
            JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                    .issuer(issuer)
                    .expirationTime(Date.from(expiresAt))
                    .subject(userDetails.getUsername())
                    .issueTime(Date.from(issuedAt))
                    .claim("scope", scope)
                    .build();

            String tokenValue = this.tokenSigner.sign(claimsSet).serialize();
            return ResponseEntity.ok(Map.of("access_token", tokenValue,
                    "token_type", BEARER.getValue(),
                    "expires_in", Duration.between(issuedAt, expiresAt).getSeconds()));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "unauthorized",
                            "error_description", e.getMessage()));
        }
    }

    @PostMapping("/token/ecsite")
    public Object issueCustomerToken(@RequestParam String username, @RequestParam String password, UriComponentsBuilder builder) {
        try {
            Authentication authenticated = customerManager.authenticate(UsernamePasswordAuthenticationToken.unauthenticated(username, password));
            UserDetails userDetails = (UserDetails) authenticated.getPrincipal();
            String issuer = builder.path("").build().toString();
            Instant issuedAt = Instant.now(this.clock);
            Instant expiresAt = issuedAt.plus(1, ChronoUnit.MINUTES);
            Set<String> scope = Set.of("role:customer");
            JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                    .issuer(issuer)
                    .expirationTime(Date.from(expiresAt))
                    .subject(userDetails.getUsername())
                    .issueTime(Date.from(issuedAt))
                    .claim("scope", scope)
                    .build();

            String tokenValue = this.tokenSigner.sign(claimsSet).serialize();
            return ResponseEntity.ok(Map.of("access_token", tokenValue,
                    "token_type", BEARER.getValue(),
                    "expires_in", Duration.between(issuedAt, expiresAt).getSeconds()));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "unauthorized",
                            "error_description", e.getMessage()));
        }
    }
}
