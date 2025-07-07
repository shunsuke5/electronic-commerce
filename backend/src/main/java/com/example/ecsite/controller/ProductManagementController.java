package com.example.ecsite.controller;

import com.example.ecsite.form.product.ProductCreateForm;
import com.example.ecsite.r2.CloudflareR2Client;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@RestController
@RequestMapping("/product")
public class ProductManagementController {

    private final CloudflareR2Client r2Client;
    private final String BUCKET_URL;

    public ProductManagementController(CloudflareR2Client r2Client, @Value("${r2.bucket-url}") String bucketUrl) {
        this.r2Client = r2Client;
        this.BUCKET_URL = bucketUrl;
    }

    @PostMapping("/create")
    public void createProduct(@ModelAttribute ProductCreateForm form, @RequestParam("image") MultipartFile file) {
        r2Client.putObject(file);
//        r2Client.listObjects();
        System.out.println(BUCKET_URL + "/" + file.getOriginalFilename());
    }
}
