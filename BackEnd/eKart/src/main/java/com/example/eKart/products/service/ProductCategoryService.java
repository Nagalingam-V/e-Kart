package com.example.eKart.products.service;

import com.example.eKart.products.data.ProductCategoryData;
import com.example.eKart.products.domain.ProductCategory;

import java.util.List;

public interface ProductCategoryService {

    ProductCategory addCategory(ProductCategoryData data);

    List<ProductCategory> getAllCategories();

}
