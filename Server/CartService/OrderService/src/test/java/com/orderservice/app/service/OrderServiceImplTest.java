package com.orderservice.app.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;

import com.orderservice.app.entity.Order;
import com.orderservice.app.entity.Cart;
import com.orderservice.app.entity.Items;
import com.orderservice.app.exception.OrderNotFoundException;
import com.orderservice.app.repository.OrderRepository;


@ExtendWith(MockitoExtension.class)
public class OrderServiceImplTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private CartClient cartClient;

    @InjectMocks
    private OrderServiceImpl orderService;

    private Order order;
    private Cart cart;

    @BeforeEach
    public void setup() {
   Items item1 = new Items(1, 50.0, 2, null, 101);
      Items  item2 = new Items(2, 25.0, 2, null, 102);
        cart = new Cart(1, Arrays.asList(item1, item2));
        cart.setTotalPrice(100.0);
        order = new Order(1, 100.0, 1, "Credit Card", new Date(), "NEW", 1);
    }

    @Test
    public void testPlaceOrder() {
        when(cartClient.getCart(order.getCartId())).thenReturn(new ResponseEntity<>(cart, HttpStatus.OK));
        when(orderRepository.save(any(Order.class))).thenReturn(order);

        ResponseEntity<Order> response = orderService.placeOrder(order);

        verify(cartClient, times(1)).deleteCart(order.getCartId());
        verify(orderRepository, times(1)).save(any(Order.class));

        assert(response.getStatusCode() == HttpStatus.OK);
        assert(response.getBody().getAmountPaid() == cart.getTotalPrice());
    }

    @Test
    public void testGetAllOrders() {
        List<Order> orders = Arrays.asList(order);
        when(orderRepository.findAll()).thenReturn(orders);

        ResponseEntity<List<Order>> response = orderService.getAllOrders();

        assert(response.getStatusCode() == HttpStatus.OK);
        assert(response.getBody().size() == 1);
    }

    @Test
    public void testGetAllOrders_NotFound() {
        when(orderRepository.findAll()).thenReturn(Arrays.asList());

        try {
            orderService.getAllOrders();
        } catch (OrderNotFoundException ex) {
            assert(ex.getMessage().equals("No any data present"));
        }
    }

    @Test
    public void testGetOrderByCustomerId() {
        List<Order> orders = Arrays.asList(order);
        when(orderRepository.findByCustomerId(order.getCustomerId())).thenReturn(orders);

        ResponseEntity<List<Order>> response = orderService.getOrderByCustomerId(order.getCustomerId());

        assert(response.getStatusCode() == HttpStatus.OK);
        assert(response.getBody().size() == 1);
    }
    @Test
    @DisplayName("Test case - Delete order")
    public void testDeleteOrder() {
        int orderId = 1;

        // Mock setup
        when(orderRepository.findById(orderId)).thenReturn(Optional.of(order));

        // Call the method under test
        ResponseEntity<String> response = orderService.deleteOrder(orderId);

        // Verify delete method invocation
        verify(orderRepository, times(1)).delete(any(Order.class)); // Ensure delete method is called

        // Assertions
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Successfully Deleted", response.getBody());
    }
    @Test
    @DisplayName("Test case - Update order status")
    public void testUpdateOrderStatus() {
        int orderId = 1;
        String newStatus = "Shipped";

        // Mock setup
        when(orderRepository.findById(orderId)).thenReturn(Optional.of(order));
        order.setOrderStatus(newStatus);
        when(orderRepository.save(any(Order.class))).thenReturn(order);

        // Call the method under test
        ResponseEntity<Order> response = orderService.changeOrderStatus(newStatus, orderId);

        // Assertion
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(newStatus, response.getBody().getOrderStatus());
    }

    @Test
    @DisplayName("Test case - Update order status with invalid order ID")
    public void testUpdateOrderStatusInvalidId() {
        int orderId = 999; // Invalid order ID
        String newStatus = "Shipped";

        // Mock setup
        when(orderRepository.findById(orderId)).thenReturn(Optional.empty());

        // Call the method under test
        Exception exception = assertThrows(OrderNotFoundException.class, () -> {
            orderService.changeOrderStatus(newStatus, orderId);
        });

        // Assertion
        assertEquals("Order not found with ID " + orderId, exception.getMessage());
    }
    @Test
    @DisplayName("Test case - Get orders by non-existent customer ID")
    public void testGetOrderByNonExistentCustomerId() {
        int nonExistentCustomerId = 999; // Non-existent customer ID

        // Mock setup
        when(orderRepository.findByCustomerId(nonExistentCustomerId)).thenReturn(Collections.emptyList());

        // Call the method under test and assert
        Exception exception = assertThrows(OrderNotFoundException.class, () -> {
            orderService.getOrderByCustomerId(nonExistentCustomerId);
        });

        // Assertion
        assertEquals("No orders!! Shop !!", exception.getMessage());
    }


}
