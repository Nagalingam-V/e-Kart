package com.example.eKart.products.util;

import java.util.UUID;

public class SkuGenerator {

    public static String generateSKU(String productName, String brand) {

        // Take first 3 letters of brand, productName
        String brandPart = brand.length() >= 3 ? brand.substring(0,3).toUpperCase() : brand.toUpperCase();
        String productPart = productName.length() >= 3 ? productName.substring(0,3).toUpperCase() : productName.toUpperCase();

        // Random 4-character unique code
        String uniqueCode = UUID.randomUUID().toString().substring(0, 4).toUpperCase();

        return brandPart + "-" + productPart + "-" + uniqueCode;
    }
}
