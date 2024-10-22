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
    @DisplayName("Test GET /orders")
    public void testGetAllOrders() throws Exception {
        // Mock data
        Order order1 = new Order(1, 100.0, 1, "Credit Card", null, "NEW", 1);
        Order order2 = new Order(2, 150.0, 2, "Cash", null, "DELIVERED", 2);
        List<Order> orders = Arrays.asList(order1, order2);

        // Mock service method
        when(orderService.getAllOrders()).thenReturn(ResponseEntity.ok(orders));

        // Perform GET request
        mockMvc.perform(get("/orders"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.size()").value(2)); // Assuming your JSON response is an array

        // Verify service method was called
        verify(orderService, times(1)).getAllOrders();
    }



    @Test
    @DisplayName("Test PUT /orders/{orderId}/status/{orderStatus}")
    public void testChangeOrderStatus() throws Exception {
        int orderId = 1;
        String orderStatus = "DELIVERED";
        Order order = new Order(orderId, 100.0, 1, "Credit Card", null, orderStatus, 1);

        // Mock service method
        when(orderService.changeOrderStatus(orderStatus, orderId)).thenReturn(ResponseEntity.ok(order));

        // Perform PUT request
        mockMvc.perform(put("/orders/{orderId}/status/{orderStatus}", orderId, orderStatus))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.orderStatus").value(orderStatus));

        // Verify service method was called
        verify(orderService, times(1)).changeOrderStatus(orderStatus, orderId);
    }


    @Test
    void testGetOrderByCustomerId() {
        // Mock data
        int customerId = 123;
        Order order1 = new Order(1, 100.0, 1, "Credit Card", new Date(), "NEW", 1);
        Order order2 = new Order(2, 150.0, 2, "Cash", new Date(), "DELIVERED", 2);
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
    	Order order = new Order(1, 100.0, 1, "Credit Card", new Date(), "NEW", 1);
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

