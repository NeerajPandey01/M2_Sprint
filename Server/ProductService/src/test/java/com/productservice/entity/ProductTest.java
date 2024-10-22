package com.productservice.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.Test;


import jakarta.validation.ConstraintViolation;
import jakarta.validation.ValidatorFactory;
 
import java.util.Set;
 
import static org.junit.jupiter.api.Assertions.assertEquals;
 
public class ProductTest {
 
    private jakarta.validation.Validator validator;
 
    public ProductTest() {
        ValidatorFactory factory = jakarta.validation.Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }
 
    @Test
    public void testProductValidation() {
        // Create a valid Product instance
        Product product = new Product(1, "Electronics", "Laptop", "Gadgets", null, 999.99, "High performance laptop", 10.0);
 
        // Validate the product (should have no violations)
        Set<ConstraintViolation<Product>> violations = validator.validate(product);
        assertEquals(0, violations.size());
    }
 

}

