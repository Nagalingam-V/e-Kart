package com.example.eKart.products.service;

import com.example.eKart.products.data.ProductData;
import com.example.eKart.products.data.ProductResponse;
import com.example.eKart.products.domain.ProductCategory;
import com.example.eKart.products.domain.ProductStatus;
import com.example.eKart.products.domain.Products;
import com.example.eKart.products.mapper.ProductMapper;
import com.example.eKart.products.repository.ProductCategoryRepository;
import com.example.eKart.products.repository.ProductsRepository;
import com.example.eKart.products.util.SkuGenerator;
import lombok.AllArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;

@Service
@AllArgsConstructor
public class ProductServiceWriteImpl {

    private final ProductsRepository productRepository;
    private final ProductCategoryRepository productCategoryRepository;

    private ProductMapper productMapper = Mappers.getMapper(ProductMapper.class);


    public Products createProduct(ProductData productData) {

        ProductCategory category =
                productCategoryRepository.findById(productData.getCategoryId())
                        .orElseThrow(() -> new RuntimeException("Invalid Category, Check the Category Once"));

        ProductStatus status = ProductStatus.fromCode(productData.getProductStatus());

        boolean inStock = productData.getStockQuantity() > 0;

        String sku = SkuGenerator.generateSKU(productData.getProductName(), productData.getBrand());

        Products product = productMapper.mapToEntity(productData);
        product.setProductCategory(category);
        product.setProductStatus(status);
        product.setInStock(inStock);
        product.setSku(sku);

        return productRepository.save(product);

    }

    public ProductResponse getProduct(Long id) {

        Products prod = productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));

        return productMapper.mapProductstoProductResponse(prod);

    }

    public List<ProductResponse> fetchAllProducts(PageRequest pageRequest) {

        List<Products> products = productRepository.findAll(pageRequest).getContent();

         return productMapper.mapProductsListtoProductResponse(products);

    }

}
