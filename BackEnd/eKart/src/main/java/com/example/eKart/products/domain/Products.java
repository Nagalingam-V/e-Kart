package com.example.eKart.products.domain;

import com.example.eKart.audit.AuditMetadata;
import com.example.eKart.products.data.ProductData;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(
        name = "products_details",
        indexes = {
                @Index(name = "idx_products_sku", columnList = "sku"),
        }
)
public class Products extends AuditMetadata {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sku", nullable = false, unique = true, length = 50)
    private String sku;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private ProductCategory productCategory;

    @Column(name = "product_name", nullable = false, length = 150)
    private String productName;


    @Column(name = "brand", nullable = false, length = 100)
    private String brand;

    @Column(name = "product_description", columnDefinition = "TEXT", nullable = false)
    private String productDescription;

    @Column(name = "product_price", nullable = false)
    private Double productPrice;

    @Column(name = "discount_price")
    private Double discountPrice;

    @Column(name = "discount_percent")
    private Double discountPercent;

    @Column(name = "stock_quantity", nullable = false)
    private Integer stockQuantity;

    @Column(name = "in_stock", nullable = false)
    private Boolean inStock;

    @Convert(converter = ProductStatusConverter.class)
    @Column(name = "product_status", nullable = false)
    private ProductStatus productStatus;

}
