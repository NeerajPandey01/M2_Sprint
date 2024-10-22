package com.profileservice.app.entity;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Setter
@Getter
@NoArgsConstructor
public class Cart {

	private int cartId;

	private double totalPrice;

	private List<Items> items;

	public Cart(int cartId, List<Items> items) {
		this.cartId = cartId;
		this.items = items;
	}
}
