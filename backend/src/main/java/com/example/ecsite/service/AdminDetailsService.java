package com.example.ecsite.service;

import com.example.ecsite.repository.AdministratorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class AdminDetailsService implements UserDetailsService {

    private final AdministratorRepository repository;

    public UserDetails loadUserByUsername(String adminName) {
        return this.repository.findByName(adminName)
                .map(
                        admin -> new User(
                                admin.getName(),
                                admin.getPassword(),
                                Collections.emptyList()
                        )
                )
                .orElseThrow(() -> new UsernameNotFoundException(adminName + " not found"));
    }
}
