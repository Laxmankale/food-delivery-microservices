package com.fooddelivery.restaurant_service.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fooddelivery.restaurant_service.entity.Restaurant;
import com.fooddelivery.restaurant_service.service.RestaurantService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {

	private final RestaurantService service;

	public RestaurantController(RestaurantService service) {
		this.service = service;
	}

	@GetMapping("/{id}")
	public ResponseEntity<Restaurant> getById(@PathVariable Long id) {
		return ResponseEntity.ok(service.getById(id));
	}

	@PostMapping
	public ResponseEntity<Restaurant> create(@Valid @RequestBody Restaurant restaurant) {
		Restaurant created = service.save(restaurant);
		return ResponseEntity.status(HttpStatus.CREATED).body(created);
	}

	@GetMapping
	public ResponseEntity<List<Restaurant>> getAll() {
		return ResponseEntity.ok(service.getAll());
	}

	@PutMapping("/{id}")
	public ResponseEntity<Restaurant> update(@PathVariable Long id, @Valid @RequestBody Restaurant restaurant) {
		return ResponseEntity.ok(service.updateRestaurant(id, restaurant));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.deleteRestaurant(id);
		return ResponseEntity.noContent().build();
	}
}
