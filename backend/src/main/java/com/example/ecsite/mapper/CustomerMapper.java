package com.example.ecsite.mapper;

import com.example.ecsite.data.dto.customer.CustomerResponseDto;
import com.example.ecsite.data.entity.Customer;
import com.example.ecsite.data.form.customer.CustomerCreateForm;
import org.mapstruct.Mapper;

@Mapper(config = ConfigMapper.class)
public interface CustomerMapper {

    CustomerResponseDto toResponseDto(Customer admin);
    Customer toEntity(CustomerCreateForm form);
}
