package com.fooddelivery.restaurant_service.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

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

	@PutMapping("/{id}")
	public Restaurant update(@PathVariable Long id, @RequestBody Restaurant restaurant) {
		return service.updateRestaurant(id, restaurant);
	}

	@DeleteMapping("/{id}")
	public String delete(@PathVariable Long id) {
		service.deleteRestaurant(id);
		return "Restaurant deleted successfully";
	}
}
