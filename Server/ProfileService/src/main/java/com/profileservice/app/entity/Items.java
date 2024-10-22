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


@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
@ToString
public class Items {

	private int itemId;

	private double price;

	private int quantity;

	private Cart cart;

	private int productId;
}
