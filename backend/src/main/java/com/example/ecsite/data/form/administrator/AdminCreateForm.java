package com.example.ecsite.data.form.administrator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AdminCreateForm {
    private String name;
    private String email;
    private String password;
}
