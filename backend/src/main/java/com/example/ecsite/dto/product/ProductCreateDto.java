package com.example.ecsite.dto.product;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class ProductCreateDto {
    private String name;
    private String genre;
    private int price;
    private int deliveryTime;
    private MultipartFile image;
}
