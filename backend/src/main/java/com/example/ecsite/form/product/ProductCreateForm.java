package com.example.ecsite.form.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@AllArgsConstructor
public class ProductCreateForm {
    private String name;
    private String genre;
    private int price;
    private int deliveryTime;
}
