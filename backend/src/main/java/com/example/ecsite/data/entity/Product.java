package com.example.ecsite.data.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
public class Product extends BaseAuditorEntity {
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false)
    private int stock;

    @Column(name = "total_purchased", nullable = false)
    private int totalPurchased;

    @Column(name = "release_date", nullable = false)
    private LocalDate releaseDate;

    @Column(name = "delivery_time", nullable = false)
    private int deliveryTime;

    @Column(name = "image_url", nullable = false)
    private String imageUrl;
}
