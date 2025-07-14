package com.example.ecsite.config;

import com.example.ecsite.security.CookieAuthenticationFilter;
import com.example.ecsite.security.JwtUtils;
import com.example.ecsite.service.AdminDetailsService;
import com.example.ecsite.service.CustomerDetailsService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.time.Clock;
import java.util.List;

@Configuration
public class SecurityConfig {

    @Bean
    @Order(1)
    public SecurityFilterChain tokenSecurityFilterChain (
            HttpSecurity http,
            UrlBasedCorsConfigurationSource source,
            JwtUtils jwtUtils) throws Exception {
        http
                .securityMatcher("/token/**")
                .securityMatcher("/admin/create")
                .cors(cors -> cors.configurationSource(source))
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/token/**").permitAll()
                        .requestMatchers("/admin/create").permitAll()
//                        .requestMatchers("/customer/create").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form.disable());

        return http.build();
    }

    @Bean
    public SecurityFilterChain securityFilterChain (
            HttpSecurity http,
            UrlBasedCorsConfigurationSource source,
            JwtUtils jwtUtils,
            @Qualifier("admin") AuthenticationManager authManager) throws Exception {
        http
                .cors(cors -> cors.configurationSource(source))
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/token/**").permitAll()
                        .requestMatchers("/admin/create").permitAll()
//                        .requestMatchers("/customer/create").permitAll()
                        .anyRequest().authenticated()
                )
                .addFilterBefore(new CookieAuthenticationFilter(jwtUtils), UsernamePasswordAuthenticationFilter.class)
                .formLogin(form -> form.disable())
                .authenticationManager(authManager);

        return http.build();
    }

    @Bean
    public UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(List.of("http://127.0.0.1:5500"));
        config.setAllowedMethods(List.of("GET","POST","PUT","DELETE","OPTIONS"));
        config.setAllowedHeaders(List.of("*"));
        config.setAllowCredentials(true);
        source.registerCorsConfiguration("/**", config);
        return source;
    }

    @Bean(name = "admin")
    @Primary
    public AuthenticationManager adminAuthenticationManager(AdminDetailsService service, PasswordEncoder encoder) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(service);
        provider.setPasswordEncoder(encoder);
        return new ProviderManager(provider);
    }

    @Bean(name = "customer")
    public AuthenticationManager customerAuthenticationManager(CustomerDetailsService service, PasswordEncoder encoder) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(service);
        provider.setPasswordEncoder(encoder);
        return new ProviderManager(provider);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
         return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtUtils jwtUtils() {
        return new JwtUtils();
    }

    @Bean
    public Clock clock() {
        return Clock.systemUTC();
    }
}