package com.example.eKart.products.controller;

import com.example.eKart.products.data.ProductData;
import com.example.eKart.products.service.ProductServiceWriteImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping(path = "/api/v1/product")
public class ProductApiResource {

    private final ProductServiceWriteImpl productServiceWrite;

    ProductApiResource(ProductServiceWriteImpl productServiceWrite){
        System.out.println("PRODUCT CONTROLLER CALLED FOR ONE");
        this.productServiceWrite = productServiceWrite;
    }

    @PostMapping(path = "/create")
    public ResponseEntity createProduct(@RequestBody ProductData productData, @RequestParam String command){
        ProductData response = null;
        if(Objects.equals(command, "create")){
            response = productServiceWrite.createProduct(productData);
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ProductData> getProduct(@PathVariable Long id){
        return ResponseEntity.ok(productServiceWrite.getProduct(id));
    }

    @PutMapping(path = "update/{id}")
    public ResponseEntity<ProductData> updateProduct(@RequestBody ProductData productData, @PathVariable Long id){
        ProductData up_res = productServiceWrite.updateProduct(id, productData);
        return ResponseEntity.ok(up_res);
    }
}
