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
public class Order {

	private int orderId;

	private double amountPaid;

	private int customerId;

	private String modeOfPayment;

	private Date orderDate;

	private String orderStatus;

	private int cartId;

}
