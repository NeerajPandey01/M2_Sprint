package com.profileservice.app.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.profileservice.app.entity.Cart;


@FeignClient(name = "cart-service",url = "http://localhost:8080")
public interface CartClient {
	@GetMapping("carts/customer/{customerId}")
	public ResponseEntity<List<Cart>> getAllByCustomerId(@PathVariable("customerId") int customerId);
}