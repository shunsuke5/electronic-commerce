package com.example.ecsite.controller;

import com.example.ecsite.data.form.product.ProductCreateForm;
import com.example.ecsite.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    @PostMapping("/create")
    public void createProduct(@ModelAttribute ProductCreateForm form, @RequestParam("image") MultipartFile file) {
        productService.register(form, file);
    }
}
