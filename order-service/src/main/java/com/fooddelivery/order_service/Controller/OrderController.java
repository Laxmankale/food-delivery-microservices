package com.fooddelivery.order_service.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fooddelivery.order_service.entity.Order;
import com.fooddelivery.order_service.service.OrderService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

	private final OrderService orderService;

	public OrderController(OrderService orderService) {
		this.orderService = orderService;
	}

	@PostMapping
	public ResponseEntity<Order> create(@Valid @RequestBody Order order) {
		Order created = orderService.createOrder(order);
		return ResponseEntity.status(HttpStatus.CREATED).body(created);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Order> getById(@PathVariable Long id) {
		return ResponseEntity.ok(orderService.getOrderById(id));
	}

	@GetMapping
	public ResponseEntity<List<Order>> getAll() {
		return ResponseEntity.ok(orderService.getAllOrders());
	}

	@PutMapping("/{id}/status")
	public ResponseEntity<Order> updateStatus(@PathVariable Long id, @RequestBody Map<String, String> body) {
		return ResponseEntity.ok(orderService.updateOrderStatus(id, body.get("status")));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> cancel(@PathVariable Long id) {
		orderService.cancelOrder(id);
		return ResponseEntity.noContent().build();
	}
}
