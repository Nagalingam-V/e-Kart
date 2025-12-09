package com.example.eKart.products.domain;

import jakarta.persistence.*;

@Converter(autoApply = true)
public class ProductStatusConverter implements AttributeConverter<ProductStatus, Integer> {
    @Override
    public Integer convertToDatabaseColumn(ProductStatus status) {
        return status != null ? status.getCode() : null;
    }

    @Override
    public ProductStatus convertToEntityAttribute(Integer code) {
        if (code == null) return null;
        return ProductStatus.fromCode(code);
    }
}
