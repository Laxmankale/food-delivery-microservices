package com.fooddelivery.restaurant_service.exception;

public class DuplicateRestaurantException extends RuntimeException {

	public DuplicateRestaurantException(String name, String location) {
		super("Restaurant already exists with name '" + name + "' at location '" + location + "'");
	}
}
