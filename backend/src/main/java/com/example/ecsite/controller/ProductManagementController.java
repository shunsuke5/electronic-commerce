package com.example.ecsite.controller;

import com.example.ecsite.form.product.ProductCreateForm;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/product")
public class ProductManagementController {

    @PostMapping("/create")
    public void createProduct(@ModelAttribute ProductCreateForm form, @RequestParam("image") MultipartFile file) {
        System.out.println(form.getName());
        System.out.println(form.getGenre());
        System.out.println(form.getPrice());
        System.out.println(form.getDeliveryTime());
        form.setImage(file);
        System.out.println(form.getImage());
    }
}
