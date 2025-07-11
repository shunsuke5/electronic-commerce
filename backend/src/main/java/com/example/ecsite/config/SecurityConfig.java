package com.example.ecsite.config;

import com.example.ecsite.service.AdminDetailsService;
import com.example.ecsite.service.CustomerDetailsService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.oauth2.core.authorization.OAuth2AuthorizationManagers.hasScope;

import java.time.Clock;

@Configuration
public class SecurityConfig {
    @Bean
    @Order(1)
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/token/**").permitAll()
                        .requestMatchers("/admin/create/").permitAll()
                        .requestMatchers("/customer/create/").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(fl -> fl.disable())
                .csrf(csrf -> csrf.disable());

        return http.build();
    }

    @Bean
    @Order(2)
    public SecurityFilterChain adminFilterChain(HttpSecurity http, @Qualifier("admin") AuthenticationManager manager) throws Exception {
        http
                .securityMatcher("/admin/**")
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().access(hasScope("role:admin"))
                        .anyRequest().authenticated())
                .authenticationManager(manager)
                .oauth2ResourceServer(oauth ->
                        oauth.jwt(jwt -> {
                        }))
                .formLogin(fl -> fl.disable())
                .csrf(csrf -> csrf.disable());
        return http.build();
    }

    @Bean
    @Order(3)
    public SecurityFilterChain customerFilterChain(HttpSecurity http, @Qualifier("customer") AuthenticationManager manager) throws Exception {
        http
                .securityMatcher("/customer/**")
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().access(hasScope("role:customer"))
                        .anyRequest().authenticated())
                .authenticationManager(manager)
                .oauth2ResourceServer(oauth ->
                        oauth.jwt(jwt -> {
                        }))
                .formLogin(fl -> fl.disable())
                .csrf(csrf -> csrf.disable());
        return http.build();
    }

    @Bean("admin")
    public AuthenticationManager authenticationManager(AdminDetailsService service, PasswordEncoder encoder) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(service);
        provider.setPasswordEncoder(encoder);
        return new ProviderManager(provider);
    }

    @Bean("customer")
    public AuthenticationManager authenticationManager(CustomerDetailsService service, PasswordEncoder encoder) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(service);
        provider.setPasswordEncoder(encoder);
        return new ProviderManager(provider);
    }

    @Bean
    public Clock clock() {
        return Clock.systemUTC();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
