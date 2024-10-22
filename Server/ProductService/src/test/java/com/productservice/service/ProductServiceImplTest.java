package com.productservice.service;




import com.productservice.entity.Product;
import com.productservice.exception.ProductNotFoundException;
import com.productservice.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    private Product testProduct;

    @BeforeEach
    public void setup() {
        // Initialize test data before each test method
        testProduct = new Product();
        testProduct.setProductId(1);
        testProduct.setProductName("Test Product");
        testProduct.setPrice(50.0);
        testProduct.setDescription("Test description");
    }



    @Test
    public void testDeleteProductById_ExistingId_DeletesProduct() {
        // Mock repository response
        when(productRepository.findById(1)).thenReturn(Optional.of(testProduct));

        // Call service method
        ResponseEntity<String> response = productService.deleteProductById(1);

        // Verify the result
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Product deleted successfully", response.getBody());
    }


    @Test
    public void testGetAllProducts_ReturnsListOfProducts() {
        // Mock repository response
        List<Product> products = new ArrayList<>();
        products.add(testProduct);
        when(productRepository.findAll()).thenReturn(products);

        // Call service method
        ResponseEntity<List<Product>> response = productService.getAllProducts();

        // Verify the result
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        assertEquals("Test Product", response.getBody().get(0).getProductName());
    }




    @Test
    public void testGetProductById_ExistingId_ReturnsProduct() {
        // Mock repository response
        when(productRepository.findById(1)).thenReturn(Optional.of(testProduct));

        // Call service method
        ResponseEntity<Product> response = productService.getProductById(1);

        // Verify the result
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Test Product", response.getBody().getProductName());
    }



}
