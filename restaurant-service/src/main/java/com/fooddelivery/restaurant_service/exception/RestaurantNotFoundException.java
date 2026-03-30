package com.fooddelivery.restaurant_service.exception;

public class RestaurantNotFoundException extends RuntimeException {

	public RestaurantNotFoundException(Long id) {
		super("Restaurant not found with id: " + id);
	}
}
