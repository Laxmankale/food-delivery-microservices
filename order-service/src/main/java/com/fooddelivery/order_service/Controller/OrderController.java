package com.fooddelivery.order_service.Controller;

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

    @GetMapping("/test")
    public String test() {
        return "Order Service OK";
    }
	@PostMapping
	public Order create(@RequestBody Order order) {
		return orderService.createOrder(order);
	}
}
