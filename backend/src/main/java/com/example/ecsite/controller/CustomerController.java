package com.example.ecsite.controller;

import com.example.ecsite.data.dto.customer.CustomerResponseDto;
import com.example.ecsite.data.form.customer.CustomerCreateForm;
import com.example.ecsite.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService service;

    @PostMapping("/create")
    public ResponseEntity<CustomerResponseDto> createCustomer(@ModelAttribute CustomerCreateForm form) {
        System.out.println("customer-create");
        return ResponseEntity.ok(this.service.create(form));
    }
}