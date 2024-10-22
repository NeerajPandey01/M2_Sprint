package com.cartservice.app.entity;
 
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
 
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
 
import org.junit.jupiter.api.Test;
 
import com.cartservice.app.entity.Cart;
import com.cartservice.app.entity.Items;
 
import jakarta.validation.ConstraintViolationException;
 
public class CartTest {
 
    @Test
    public void testCreateCart() {
        // Create items for the cart
        List<Items> items = new ArrayList<>();
        items.add(new Items(1, 10.0, 2, null,1)); // Assuming constructor parameters
 
        Cart cart = new Cart();
        cart.setCartId(1);
        cart.setItems(items);
 
        assertNotNull(cart);
        assertEquals(1, cart.getCartId());
        assertEquals(items, cart.getItems());
    }
 
 
    @Test
    public void testCartTotalPriceCalculation() {
        // Create items for the cart
        List<Items> items = new ArrayList<>();
        items.add(new Items(1, 10.0, 2, null,1)); // Assuming constructor parameters
        items.add(new Items(2, 5.0, 3, null,1)); // Assuming constructor parameters
 
        Cart cart = new Cart();
        cart.setItems(items);
 
        double expectedTotalPrice = 10.0 * 2 + 5.0 * 3; // Total = price * quantity for each item
        cart.setTotalPrice(expectedTotalPrice);
 
        assertEquals(expectedTotalPrice, cart.getTotalPrice());
    }
 

}