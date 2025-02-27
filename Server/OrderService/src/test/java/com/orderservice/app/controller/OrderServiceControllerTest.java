package com.orderservice.app.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.orderservice.app.controller.OrderServiceController;
import com.orderservice.app.entity.Order;
import com.orderservice.app.service.OrderService;
import com.orderservice.app.service.OrderServiceImpl;

public class OrderServiceControllerTest {

    private MockMvc mockMvc;

    @Mock
    private OrderService orderService;

    @InjectMocks
    private OrderServiceController orderServiceController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(orderServiceController).build();
    }




   

    @Test
    void testGetOrderByCustomerId() {
        // Mock data
        int customerId = 123;
        Order order1 = new Order(1, 100.0, 1, "Credit Card", new Date(), "NEW", null, 1);
        Order order2 = new Order(2, 150.0, 2, "Cash", new Date(), "DELIVERED", null, 2);
        List<Order> orders = Arrays.asList(order1, order2);
        ResponseEntity<List<Order>> expectedResponse = ResponseEntity.ok(orders);

        // Mock behavior of orderService.getOrderByCustomerId
        when(orderService.getOrderByCustomerId(customerId)).thenReturn(expectedResponse);

        // Call the controller method
        ResponseEntity<List<Order>> actualResponse = orderServiceController.getOrderByCustomerId(customerId);

        // Assert the response
        assertEquals(HttpStatus.OK, actualResponse.getStatusCode());
        assertEquals(orders, actualResponse.getBody());
    }
    @Test
    void testPlaceOrder() {
        // Mock data
    	Order order = new Order(1, 100.0, 1, "Credit Card", new Date(), "NEW", null, 1);
        ResponseEntity<Order> expectedResponse = ResponseEntity.ok(order);

        // Mock behavior of orderService.placeOrder
        when(orderService.placeOrder(order)).thenReturn(expectedResponse);

        // Call the controller method
        ResponseEntity<Order> actualResponse = orderServiceController.placeOrder(order);

        // Assert the response
        assertEquals(HttpStatus.OK, actualResponse.getStatusCode());
        assertEquals(order, actualResponse.getBody());
    }
    
    @Test
    void testDeleteOrder() {
        // Mock data
        int orderId = 1;
        ResponseEntity<String> expectedResponse = ResponseEntity.ok("Order deleted successfully");

        // Mock behavior of orderService.deleteOrder
        when(orderService.deleteOrder(orderId)).thenReturn(expectedResponse);

        // Call the controller method
        ResponseEntity<String> actualResponse = orderServiceController.deleteOrder(orderId);

        // Assert the response
        assertEquals(HttpStatus.OK, actualResponse.getStatusCode());
        assertEquals("Order deleted successfully", actualResponse.getBody());
    }
}

