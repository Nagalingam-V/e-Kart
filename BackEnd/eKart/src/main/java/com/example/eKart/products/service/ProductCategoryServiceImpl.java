package com.example.eKart.products.service;

import com.example.eKart.products.data.ProductCategoryData;
import com.example.eKart.products.domain.ProductCategory;
import com.example.eKart.products.repository.ProductCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class ProductCategoryServiceImpl implements ProductCategoryService{

    private final ProductCategoryRepository categoryRepository;

    @Override
    public ProductCategory addCategory(ProductCategoryData data) {
        ProductCategory category = new ProductCategory();
        category.setCategoryName(data.getCategoryName());
        return categoryRepository.save(category);
    }

    @Override
    public List<ProductCategory> getAllCategories() {
        return categoryRepository.findAll();
    }
}
