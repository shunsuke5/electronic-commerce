package com.example.ecsite.controller;

import com.example.ecsite.dto.product.ProductCreateDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductManagementController {

    @PostMapping("/create")
    public void createProduct(@ModelAttribute ProductCreateDto dto) {
        System.out.println(dto.getImage());
    }
}
