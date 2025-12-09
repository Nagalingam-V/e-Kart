package com.example.eKart.products.controller;

import com.example.eKart.products.data.ProductCategoryData;
import com.example.eKart.products.domain.ProductCategory;
import com.example.eKart.products.service.ProductCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class ProductCategoryController {


    private final ProductCategoryService categoryService;

    @PostMapping
    public ResponseEntity<ProductCategory> addCategory(
            @RequestBody ProductCategoryData data
    ) {
        return ResponseEntity.ok(categoryService.addCategory(data));
    }

    @GetMapping
    public ResponseEntity<List<ProductCategory>> getCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

}
