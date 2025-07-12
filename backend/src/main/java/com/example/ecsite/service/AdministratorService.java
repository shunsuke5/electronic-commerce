package com.example.ecsite.service;

import com.example.ecsite.data.dto.administrator.AdminResponseDto;
import com.example.ecsite.data.entity.Administrator;
import com.example.ecsite.data.form.administrator.AdminCreateForm;
import com.example.ecsite.mapper.AdministratorMapper;
import com.example.ecsite.repository.AdministratorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdministratorService {

    private final AdministratorMapper mapper;
    private final AdministratorRepository repository;

    public AdminResponseDto create(AdminCreateForm form) {
        Administrator admin = this.mapper.toEntity(form);
        Administrator result = this.repository.save(admin);
        return this.mapper.toResponseDto(result);
    }
}
