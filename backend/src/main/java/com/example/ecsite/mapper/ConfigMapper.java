package com.example.ecsite.mapper;

import org.mapstruct.MapperConfig;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.ReportingPolicy;

@MapperConfig(
        componentModel = "spring", // Spring Beanとして登録
        unmappedTargetPolicy = ReportingPolicy.IGNORE, // 異なるプロパティを無視する
        nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL) // マッピング元がNULLなら、マッピング先にNULLを設定
public interface ConfigMapper {
}
