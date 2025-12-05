package com.example.eKart.products.service;

import com.example.eKart.products.data.ProductData;
import com.example.eKart.products.entity.Products;
import com.example.eKart.products.repository.ProductsRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class ProductServiceWriteImpl {

    private final ProductsRepository productRepository;

    ProductServiceWriteImpl(ProductsRepository productRepository){
        this.productRepository = productRepository;
    }

    public ProductData createProduct(ProductData productData) {
        Products products = Products.createNewProductWithData(productData);
        Products savedProducts = productRepository.saveAndFlush(products);
        return ProductData.fromEntity(savedProducts);
    }

    public ProductData getProduct(Long id) {
        Products prod = productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));
        return ProductData.fromEntity(prod);
    }

    public ProductData updateProduct(Long id, ProductData productData) {
        Products existing = productRepository.findById(id).orElseThrow();

        existing.setProduct_Name(productData.getProduct_Name());
        existing.setProduct_category(productData.getProduct_category());
        existing.setProduct_price(productData.getProduct_price());

        Products updated = productRepository.save(existing);

        return ProductData.fromEntity(updated);
    }
}
