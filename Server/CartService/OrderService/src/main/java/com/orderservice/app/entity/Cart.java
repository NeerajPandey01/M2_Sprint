package com.orderservice.app.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Cart {
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int cartId;

	    private double totalPrice;

	    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	    @JoinColumn(name = "cart_id")
	    @JsonManagedReference
	    private List<Items> items;

	    public Cart(int cartId, List<Items> items) {
	        this.cartId = cartId;
	        this.items = items;
	    }
}
