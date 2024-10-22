package com.cartservice.app.service;


import com.cartservice.app.entity.Cart;
import com.cartservice.app.entity.Items;
import com.cartservice.app.entity.Product;
import com.cartservice.app.exception.CartServiceException;
import com.cartservice.app.repository.CartRepository;
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
class CartServiceImplTest {

    @Mock
    private CartRepository cartRepository;

    @Mock
    private ProductFeignClient productFeignClient;

    @InjectMocks
    private CartServiceImpl cartService;

    private Cart testCart;
    private List<Cart> testCarts;

    @BeforeEach
    public void setup() {
        // Initialize test data before each test method
        testCart = new Cart();
        testCart.setCartId(1);
        testCart.setCustomerId(123);
        testCart.setTotalPrice(0); // Set your initial price if needed

        Items item1 = new Items();
        item1.setItemId(1);
        item1.setProductId(101);
        item1.setPrice(50.0);
        item1.setQuantity(2);

        Items item2 = new Items();
        item2.setItemId(2);
        item2.setProductId(102);
        item2.setPrice(75.0);
        item2.setQuantity(1);

        List<Items> items = new ArrayList<>();
        items.add(item1);
        items.add(item2);

        testCart.setItems(items);

        testCarts = new ArrayList<>();
        testCarts.add(testCart);
    }

    @Test
    public void testGetCartById_ExistingId_ReturnsCart() {
        // Mock repository response
        when(cartRepository.findById(1)).thenReturn(Optional.of(testCart));

        // Call service method
        ResponseEntity<Cart> response = cartService.getcartById(1);

        // Verify the result
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(123, response.getBody().getCustomerId());
    }



    @Test
    public void testGetAllCarts_ReturnsListOfCarts() {
        // Mock repository response
        when(cartRepository.findAll()).thenReturn(testCarts);

        // Call service method
        ResponseEntity<List<Cart>> response = cartService.getallcarts();

        // Verify the result
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
    }

 

    
    @Test
    public void testCartTotal_CalculatesCorrectTotal() {
        // Call service method
        double total = cartService.cartTotal(testCart);

        // Verify the result
        assertEquals(175.0, total);
    }
    








}
