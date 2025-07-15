package com.example.ecsite.controller;

import com.example.ecsite.data.entity.Administrator;
import com.example.ecsite.data.form.administrator.AdminLoginForm;
import com.example.ecsite.repository.AdministratorRepository;
import com.example.ecsite.security.JwtUtils;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AdministratorRepository repository;
    private final PasswordEncoder encoder;
    private final JwtUtils jwtUtils;

    @GetMapping("/auth")
    public void auth() {
        System.out.println("auth");
    }

    @PostMapping("/token/admin")
    public ResponseEntity<?> login(@ModelAttribute AdminLoginForm form, HttpServletResponse response) {
        try {
            Administrator admin = repository.findByName(form.getName())
                    .orElseThrow(() -> new HttpClientErrorException(HttpStatus.UNAUTHORIZED, "401エラー"));

            if (!encoder.matches(form.getPassword(), admin.getPassword())) {
                throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED, "401エラー");
            }

            String token = jwtUtils.generateToken(admin.getName(), admin.getId());
            System.out.println(token);
            Cookie cookie = new Cookie("JWT_TOKEN", token);

            cookie.setHttpOnly(true);
            cookie.setSecure(true);
            cookie.setPath("/");
            cookie.setMaxAge(60);
            cookie.setAttribute("SameSite", "None");
            response.addCookie(cookie);

            return ResponseEntity.ok(Map.of("message", "ログイン成功"));
        } catch (HttpClientErrorException e) {
            return ResponseEntity.status(401).body(e.getMessage());
        }
    }
}
