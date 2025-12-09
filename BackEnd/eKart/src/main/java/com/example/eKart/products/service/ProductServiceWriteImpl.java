package com.example.eKart.products.service;

import com.example.eKart.products.data.ProductData;
import com.example.eKart.products.domain.ProductCategory;
import com.example.eKart.products.domain.ProductStatus;
import com.example.eKart.products.domain.Products;
import com.example.eKart.products.repository.ProductCategoryRepository;
import com.example.eKart.products.repository.ProductsRepository;
import com.example.eKart.products.util.SkuGenerator;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@AllArgsConstructor
public class ProductServiceWriteImpl {

    private final ProductsRepository productRepository;
    private final ProductCategoryRepository productCategoryRepository;


    public Products createProduct(ProductData productData) {

        ProductCategory category =
                productCategoryRepository.findById(productData.getCategoryId())
                        .orElseThrow(() -> new RuntimeException("Invalid Category, Check the Category Once"));

        ProductStatus status = ProductStatus.fromCode(productData.getProductStatus());

        boolean inStock = productData.getStockQuantity() > 0;

        String sku = SkuGenerator.generateSKU(productData.getProductName(), productData.getBrand());

        Products product =
                Products
                        .builder()
                        .sku(sku)
                        .productCategory(category)
                        .productName(productData.getProductName())
                        .brand(productData.getBrand())
                        .productDescription(productData.getProductDescription())
                        .productPrice(productData.getProductPrice())
                        .discountPrice(productData.getDiscountPrice())
                        .discountPercent(productData.getDiscountPercent())
                        .stockQuantity(productData.getStockQuantity())
                        .inStock(inStock)
                        .productStatus(status)
                        .build();

        return productRepository.save(product);

    }

//    public ProductData getProduct(Long id) {
//        Products prod = productRepository.findById(id)
//                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));
//        return ProductData.fromEntity(prod);
//    }




}
