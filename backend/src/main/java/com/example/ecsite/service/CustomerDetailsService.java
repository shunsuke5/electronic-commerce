package com.example.ecsite.service;

import com.example.ecsite.repository.CustomerRepository;
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
public class CustomerDetailsService implements UserDetailsService {

    private final CustomerRepository repository;

    public UserDetails loadUserByUsername(String email) {
        return this.repository.findByEmail(email)
                .map(
                        customer -> new User(
                                customer.getEmail(),
                                customer.getPassword(),
                                List.of(new SimpleGrantedAuthority("authority:customer"))
                        )
                )
                .orElseThrow(() -> new UsernameNotFoundException(email + " not found"));
    }
}
