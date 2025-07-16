package com.example.ecsite.controller;

import com.example.ecsite.data.entity.Administrator;
import com.example.ecsite.data.entity.Customer;
import com.example.ecsite.data.form.administrator.AdminLoginForm;
import com.example.ecsite.repository.AdministratorRepository;
import com.example.ecsite.repository.CustomerRepository;
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

    private final AdministratorRepository adminRepository;
    private final CustomerRepository customerRepository;
    private final PasswordEncoder encoder;
    private final JwtUtils jwtUtils;

    @GetMapping("/admin/auth")
    public void adminAuth() {
        System.out.println("/admin/auth");
    }

    @GetMapping("/customer/auth")
    public void customerAuth() {
        System.out.println("/customer/auth");
    }

    @PostMapping("/token/admin")
    public ResponseEntity<?> admin(@ModelAttribute AdminLoginForm form, HttpServletResponse response) {
        try {
            Administrator admin = adminRepository.findByName(form.getName())
                    .orElseThrow(() -> new HttpClientErrorException(HttpStatus.UNAUTHORIZED, "401エラー"));

            if (!encoder.matches(form.getPassword(), admin.getPassword())) {
                throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED, "401エラー");
            }

            String token = jwtUtils.generateAdminToken(admin.getName(), admin.getId());
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

    @PostMapping("/token/customer")
    public ResponseEntity<?> customer(@ModelAttribute AdminLoginForm form, HttpServletResponse response) {
        try {
            Customer customer = customerRepository.findByName(form.getName())
                    .orElseThrow(() -> new HttpClientErrorException(HttpStatus.UNAUTHORIZED, "401エラー"));

            if (!encoder.matches(form.getPassword(), customer.getPassword())) {
                throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED, "401エラー");
            }

            String token = jwtUtils.generateCustomerToken(customer.getName(), customer.getId());
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
