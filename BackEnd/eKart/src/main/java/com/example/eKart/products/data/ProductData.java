package com.example.eKart.products.data;

import com.example.eKart.products.domain.Products;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductData {
    private Long id;
    private String sku;
    private Long categoryId;        // Only ID instead of full object
    private String productName;
    private String brand;
    private String productDescription;
    private Double productPrice;
    private Double discountPrice;
    private Double discountPercent;
    private Integer stockQuantity;
    private Boolean inStock;
    private Integer productStatus;
}
