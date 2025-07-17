package com.example.ecsite.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Collections;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtils {

    private final SecretKey SECRET_KEY = Jwts.SIG.HS256.key().build();
    private final long EXPIRATION_TIME = 86400000;

    public String generateAdminToken(String username, Long id) {
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .claims(Map.of("id", id, "scope","authority:admin"))
                .signWith(SECRET_KEY)
                .compact();
    }

    public String generateCustomerToken(String username, Long id) {
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .claims(Map.of("id", id, "scope","authority:customer"))
                .signWith(SECRET_KEY)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().verifyWith(SECRET_KEY).build().parseSignedClaims(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            System.err.println("Invalid JWT token: " + e.getMessage());
            return false;
        }
    }

    public Authentication getAuthentication(String token) {
        String username = getUsernameFromToken(token);
        GrantedAuthority authority = getRoleFromToken(token);
        User principal = new User(username, "", Collections.singletonList(authority));
        return new UsernamePasswordAuthenticationToken(principal, null, principal.getAuthorities());
    }

    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public Long getIdFromToken(String token) {
        return getClaimFromToken(token, claims -> claims.get("id", Long.class));
    }

    public GrantedAuthority getRoleFromToken(String token) {
        String role = getClaimFromToken(token, claims -> claims.get("scope", String.class));
        return new SimpleGrantedAuthority(role);
    }

    private <T> T getClaimFromToken(String token, Function<Claims, T> resolver) {
        final Claims claims = Jwts.parser()
                .verifyWith(SECRET_KEY)
                .build()
                .parseSignedClaims(token)
                .getPayload();

        return resolver.apply(claims);
    }
}
