package com.example.ecsite.controller;

import com.example.ecsite.data.entity.Administrator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdministratorController {

    @PostMapping("/create")
    public ResponseEntity<Administrator> createAdmin() {

    }
}
