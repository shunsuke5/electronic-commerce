package com.example.ecsite.service;

import com.example.ecsite.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class CustomerDetailsService implements UserDetailsService {

    private final CustomerRepository repository;

    public UserDetails loadUserByUsername(String customerName) {
        return repository.findByName(customerName)
                .map(
                        customer -> new User(
                                customer.getName(),
                                customer.getPassword(),
                                Collections.emptyList()
                        )
                )
                .orElseThrow(() -> new UsernameNotFoundException(customerName + " not found"));
    }
}
