package com.example.eKart.products.mapper;

import com.example.eKart.products.data.ProductData;
import com.example.eKart.products.data.ProductResponse;
import com.example.eKart.products.domain.ProductCategory;
import com.example.eKart.products.domain.Products;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    //convert entity to DTO
    @Mapping(source = "productCategory", target = "categoryId", qualifiedByName = "categoryId")
    @Mapping(source = "productCategory", target = "categoryName", qualifiedByName = "categoryName")
    ProductResponse mapProductstoProductResponse(Products product);

    @Named("categoryId")
    static Long getCategoryId(ProductCategory productCategory){
        return productCategory != null ? productCategory.getId() : null;
    }

    @Named("categoryName")
    static String getCategoryName(ProductCategory productCategory){
        return productCategory != null ? productCategory.getCategoryName() : null;
    }

    //convert List of Entity to List of DTO
    List<ProductResponse> mapProductsListtoProductResponse(List<Products> product);

    //convert DTO to Entity
    @Mapping(target = "productCategory", ignore =true)
    @Mapping(target = "sku", ignore =true)
    @Mapping(target = "inStock", ignore =true)
    @Mapping(target = "productStatus", ignore =true)
    Products mapToEntity(ProductData productData);

}
