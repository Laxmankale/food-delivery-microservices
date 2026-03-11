package com.fooddelivery.restaurant_service.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fooddelivery.restaurant_service.entity.Restaurant;
import com.fooddelivery.restaurant_service.service.RestaurantService;

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {

	private final RestaurantService service;

	public RestaurantController(RestaurantService service) {
		super();
		this.service = service;
	}

	@GetMapping("/{id}")
	public Restaurant getById(@PathVariable Long id) {
	    return service.getById(id);
	}
	@PostMapping
	public Restaurant create(@RequestBody Restaurant restaurant) {
		return service.save(restaurant);
	}

	@GetMapping
	public List<Restaurant> getAll() {
		return service.getAll();
	}
}
