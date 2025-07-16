package com.example.ecsite.data.form.customer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CustomerCreateForm {
    private String name;
    private String password;
}
