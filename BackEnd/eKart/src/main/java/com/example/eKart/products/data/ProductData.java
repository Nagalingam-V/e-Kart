package com.example.eKart.products.data;

import com.example.eKart.products.entity.Products;
import lombok.Data;


public class ProductData {
    private Long id;
    private String product_category;
    private String product_Name;
    private Double product_price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProduct_category() {
        return product_category;
    }

    public void setProduct_category(String product_category) {
        this.product_category = product_category;
    }

    public String getProduct_Name() {
        return product_Name;
    }

    public void setProduct_Name(String product_Name) {
        this.product_Name = product_Name;
    }

    public Double getProduct_price() {
        return product_price;
    }

    public void setProduct_price(Double product_price) {
        this.product_price = product_price;
    }

    public static ProductData fromEntity(Products entity) {
        ProductData pd = new ProductData();
        pd.setId(entity.getId());
        pd.setProduct_category(entity.getProduct_category());
        pd.setProduct_Name(entity.getProduct_Name());
        pd.setProduct_price(entity.getProduct_price());
        return pd;
    }
}
