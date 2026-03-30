package com.fooddelivery.order_service.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.*;

import com.fooddelivery.order_service.entity.Order;
import com.fooddelivery.order_service.service.OrderService;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

	private final OrderService orderService;

	public OrderController(OrderService orderService) {
		super();
		this.orderService = orderService;
	}

	@PostMapping
	public Order create(@RequestBody Order order) {
		return orderService.createOrder(order);
	}

	@GetMapping("/{id}")
	public Order getById(@PathVariable Long id) {
		return orderService.getOrderById(id);
	}

	@GetMapping
	public List<Order> getAll() {
		return orderService.getAllOrders();
	}

	@PutMapping("/{id}/status")
	public Order updateStatus(@PathVariable Long id, @RequestBody Map<String, String> body) {
		return orderService.updateOrderStatus(id, body.get("status"));
	}

	@DeleteMapping("/{id}")
	public String cancel(@PathVariable Long id) {
		orderService.cancelOrder(id);
		return "Order cancelled successfully";
	}
}
