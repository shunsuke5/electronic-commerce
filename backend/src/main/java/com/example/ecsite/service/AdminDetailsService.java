package com.example.ecsite.service;

import com.example.ecsite.repository.AdministratorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminDetailsService implements UserDetailsService {

    private final AdministratorRepository repository;

    public UserDetails loadUserByUsername(String email) {
        return this.repository.findByEmail(email)
                .map(
                        admin -> new User(
                                admin.getEmail(),
                                admin.getPassword(),
                                List.of(new SimpleGrantedAuthority("authority:admin"))
                        )
                )
                .orElseThrow(() -> new UsernameNotFoundException(email + " not found"));
    }
}
