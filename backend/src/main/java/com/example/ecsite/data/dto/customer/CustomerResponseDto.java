package com.example.ecsite.data.dto.customer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CustomerResponseDto {
    private Long id;
    private String email;
}
