package com.example.ecsite.controller;

import com.example.ecsite.data.dto.administrator.AdminResponseDto;
import com.example.ecsite.data.form.administrator.AdminCreateForm;
import com.example.ecsite.service.AdministratorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdministratorController {

    private final AdministratorService service;

    @PostMapping("/create")
    public ResponseEntity<AdminResponseDto> createAdmin(@ModelAttribute AdminCreateForm form) {
        System.out.println("controller-create");
        return ResponseEntity.ok(this.service.create(form));
    }
}
