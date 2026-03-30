package com.fooddelivery.order_service.exception;

public class InvalidOrderStatusException extends RuntimeException {

	public InvalidOrderStatusException(String status) {
		super("Invalid order status: " + status);
	}
}
