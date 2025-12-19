package com.example.eKart.products.controller;

import com.example.eKart.products.data.ProductData;
import com.example.eKart.products.data.ProductResponse;
import com.example.eKart.products.domain.Products;
import com.example.eKart.products.service.ProductServiceWriteImpl;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(path = "/api/v1/product")
@AllArgsConstructor
public class ProductApiResource {

    private final ProductServiceWriteImpl productServiceWrite;

    @PostMapping(path = "/create")
    public ResponseEntity<Products> createProduct(@RequestBody ProductData productData, @RequestParam String command) {
        Products response = null;
        if (Objects.equals(command, "create")) {
            response = productServiceWrite.createProduct(productData);
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ProductResponse> getProduct(@PathVariable Long id) {
        return ResponseEntity.ok(productServiceWrite.getProduct(id));
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> fetchAllProducts(@RequestParam(required = false, defaultValue = "5") int pageSize,
                                                                  @RequestParam(required = false, defaultValue = "1") int pageNo,
                                                                  @RequestParam(required = false, defaultValue = "id") String sortBy,
                                                                  @RequestParam(required = false, defaultValue = "ASC") String sortDir) {

        Sort.Direction direction = (sortDir.equalsIgnoreCase("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC);
        return ResponseEntity.ok(productServiceWrite.fetchAllProducts(PageRequest.of(pageNo - 1, pageSize, Sort.by(direction, sortBy))));
    }

}
