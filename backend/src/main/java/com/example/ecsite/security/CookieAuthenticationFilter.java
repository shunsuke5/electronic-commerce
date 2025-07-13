package com.example.ecsite.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;

@RequiredArgsConstructor
public class CookieAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtils jwtUtils;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            Arrays.stream(cookies)
                    .filter(cookie -> "JWT_TOKEN".equals(cookie.getName()))
                    .findFirst()
                    .ifPresentOrElse(cookie -> {
                        String token = cookie.getValue();

                        if (jwtUtils.validateToken(token)) {
                            Authentication auth = jwtUtils.getAuthentication(token);
                            SecurityContextHolder.getContext().setAuthentication(auth);
                        } else {
                            System.out.println("JWT validation failed.");
                        }
                    },
                            () -> System.out.println("JWT Token not found in cookkies."));
        } else {
            System.out.println("No cookies found in the request.");
        }

        filterChain.doFilter(request, response);
        System.out.println("Response Status: " + response.getStatus());
    }
}
