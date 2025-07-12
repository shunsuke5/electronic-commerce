package com.example.ecsite.mapper;

import com.example.ecsite.data.dto.administrator.AdminResponseDto;
import com.example.ecsite.data.entity.Administrator;
import com.example.ecsite.data.form.administrator.AdminCreateForm;
import org.mapstruct.Mapper;

@Mapper(config = ConfigMapper.class)
public interface AdministratorMapper {

    AdminResponseDto toResponseDto(Administrator admin);
    Administrator toEntity(AdminCreateForm form);
}
