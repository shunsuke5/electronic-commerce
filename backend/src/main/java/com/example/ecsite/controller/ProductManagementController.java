package com.example.ecsite.controller;

import com.example.ecsite.form.product.ProductCreateForm;
import com.example.ecsite.r2.CloudflareR2Client;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductManagementController {

    private final CloudflareR2Client r2Client;

    @PostMapping("/create")
    public void createProduct(@ModelAttribute ProductCreateForm form, @RequestParam("image") MultipartFile file) {
        r2Client.putObject("ecsite", file);
    }
}
