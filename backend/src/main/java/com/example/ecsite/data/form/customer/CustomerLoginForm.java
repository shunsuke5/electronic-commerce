package com.example.ecsite.data.form.customer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CustomerLoginForm {
    private String email;
    private String password;
}
