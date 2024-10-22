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
        order = new Order(1, 100.0, 1, "Credit Card", new Date(), "NEW", null, 1);
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

    
}
