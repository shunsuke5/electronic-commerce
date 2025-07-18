package com.example.ecsite.service;

import com.example.ecsite.data.dto.customer.CustomerResponseDto;
import com.example.ecsite.data.entity.Customer;
import com.example.ecsite.data.form.customer.CustomerCreateForm;
import com.example.ecsite.mapper.CustomerMapper;
import com.example.ecsite.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository repository;
    private final CustomerMapper mapper;
    private final PasswordEncoder encoder;

    public CustomerResponseDto create(CustomerCreateForm form) {
        Customer customer = this.mapper.toEntity(form);
        customer.setPassword(encoder.encode(customer.getPassword()));
        Customer result = this.repository.save(customer);
        return this.mapper.toResponseDto(result);
    }
}
