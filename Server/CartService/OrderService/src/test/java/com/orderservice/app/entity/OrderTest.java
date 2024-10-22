package com.orderservice.app.entity;


import org.junit.jupiter.api.Test;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderTest {

    @Test
    public void testGettersAndSetters() {
        int orderId = 1;
        double amountPaid = 100.0;
        int customerId = 10;
        String modeOfPayment = "Credit Card";
        Date orderDate = new Date();
        String orderStatus = "Completed";
        int cartId = 1;

        Order order = new Order();
        
        order.setOrderId(orderId);
        order.setAmountPaid(amountPaid);
        order.setCustomerId(customerId);
        order.setModeOfPayment(modeOfPayment);
        order.setOrderDate(orderDate);
        order.setOrderStatus(orderStatus);
        order.setCartId(cartId);

        assertEquals(orderId, order.getOrderId());
        assertEquals(amountPaid, order.getAmountPaid());
        assertEquals(customerId, order.getCustomerId());
        assertEquals(modeOfPayment, order.getModeOfPayment());
        assertEquals(orderDate, order.getOrderDate());
        assertEquals(orderStatus, order.getOrderStatus());
        assertEquals(cartId, order.getCartId());
    
    }
    
    
    
}

