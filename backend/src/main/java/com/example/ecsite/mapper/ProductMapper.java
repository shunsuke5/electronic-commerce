package com.example.ecsite.mapper;

import com.example.ecsite.data.dto.product.ProductCreateDto;
import com.example.ecsite.data.entity.Product;
import com.example.ecsite.data.form.product.ProductCreateForm;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface ProductMapper {

    @Mapping(target = "categoryId", source = "categoryId")
    @Mapping(target = "imageUrl", source = "imageUrl")
    ProductCreateDto toDto(ProductCreateForm form, Long companyId, Long categoryId, String imageUrl);

    Product toEntity(ProductCreateDto dto);
}
