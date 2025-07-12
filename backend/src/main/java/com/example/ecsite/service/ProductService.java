package com.example.ecsite.service;

import com.example.ecsite.data.form.product.ProductCreateForm;
import com.example.ecsite.r2.CloudflareR2Client;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ProductService {

    private final CloudflareR2Client r2Client;
    private final String BUCKET_URL;

    public ProductService(CloudflareR2Client r2Client, @Value("${r2.bucket-url}") String bucketUrl) {
        this.r2Client = r2Client;
        this.BUCKET_URL = bucketUrl;
    }

    public void register(ProductCreateForm form, MultipartFile file) {
        this.r2Client.putObject(file);
        System.out.println(BUCKET_URL + "/" + file.getOriginalFilename());
    }
}
