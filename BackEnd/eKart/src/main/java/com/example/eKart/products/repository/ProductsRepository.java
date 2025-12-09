package com.example.eKart.products.repository;

import com.example.eKart.products.domain.Products;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductsRepository extends JpaRepository<Products , Long> {
}
