package com.productservice.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.productservice.entity.Product;

import jakarta.validation.Valid;

public interface ProductService {

	ResponseEntity<Product> addProduct(@Valid Product product);

	ResponseEntity<String> deleteProductById(int productId);

	ResponseEntity<List<Product>> getAllProducts();

	ResponseEntity<Product> updateProducts(int productId, @Valid Product product);

	ResponseEntity<Product> getProductById(int productId);



}
