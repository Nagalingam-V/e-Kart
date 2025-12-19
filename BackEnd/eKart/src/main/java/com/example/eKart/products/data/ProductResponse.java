package com.example.eKart.products.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {
    private Long id;
    private String sku;
    private Long categoryId;   // Only ID instead of full object
    private String categoryName;
    private String productName;
    private String brand;
    private String productDescription;
    private Double productPrice;
    private Double discountPrice;
    private Double discountPercent;
    private Integer stockQuantity;
    private Boolean inStock;
    private String productStatus;

}
