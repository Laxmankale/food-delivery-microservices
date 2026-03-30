package com.fooddelivery.restaurant_service.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fooddelivery.restaurant_service.entity.Restaurant;
import com.fooddelivery.restaurant_service.exception.RestaurantNotFoundException;
import com.fooddelivery.restaurant_service.repository.RestaurantRepository;

@Service
public class RestaurantService {

	private final RestaurantRepository repository;

	public RestaurantService(RestaurantRepository repository) {
		this.repository = repository;
	}

	public Restaurant save(Restaurant restaurant) {
		return repository.save(restaurant);
	}

	public Restaurant getById(Long id) {
		return repository.findById(id)
				.orElseThrow(() -> new RestaurantNotFoundException(id));
	}

	public List<Restaurant> getAll() {
		return repository.findAll();
	}

	public Restaurant updateRestaurant(Long id, Restaurant restaurantDetails) {
		Restaurant restaurant = repository.findById(id)
				.orElseThrow(() -> new RestaurantNotFoundException(id));
		restaurant.setName(restaurantDetails.getName());
		restaurant.setLocation(restaurantDetails.getLocation());
		return repository.save(restaurant);
	}

	public void deleteRestaurant(Long id) {
		Restaurant restaurant = repository.findById(id)
				.orElseThrow(() -> new RestaurantNotFoundException(id));
		repository.delete(restaurant);
	}
}
