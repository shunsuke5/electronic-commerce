package com.example.ecsite.mapper;

import org.mapstruct.Mapper;

@Mapper(config = ConfigMapper.class)
public interface ProductMapper {

//    @Mapping(target = "categoryId", source = "categoryId")
//    @Mapping(target = "imageUrl", source = "imageUrl")
//    ProductCreateDto toDto(ProductCreateForm form, Long categoryId, String imageUrl);

//    Product toEntity(ProductCreateDto dto);
}
