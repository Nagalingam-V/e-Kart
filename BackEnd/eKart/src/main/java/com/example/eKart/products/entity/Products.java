package com.example.eKart.products.entity;

import com.example.eKart.products.data.ProductData;
import jakarta.persistence.*;

@Entity
@Table(name = "products_details")
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String product_category;
    private String product_Name;
    private Double product_price;

    public Products(){

    }
    private Products(Long id, String product_category, String product_Name, Double product_price) {
        this.id = id;
        this.product_category = product_category;
        this.product_Name = product_Name;
        this.product_price = product_price;
    }

    public static Products createNewProductWithData(ProductData productData){
        return new Products(productData.getId(), productData.getProduct_category(),productData.getProduct_Name(),productData.getProduct_price());
    }


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
}
