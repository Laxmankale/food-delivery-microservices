package com.fooddelivery.order_service.service;

import org.springframework.stereotype.Service;

import com.fooddelivery.order_service.client.RestaurantClient;
import com.fooddelivery.order_service.entity.Order;
import com.fooddelivery.order_service.repository.OrderRepository;

@Service
public class OrderService {

	private final OrderRepository orderRepository;
	private final RestaurantClient restaurantClient;

	public OrderService(OrderRepository orderRepository, RestaurantClient restaurantClient) {
		super();
		this.orderRepository = orderRepository;
		this.restaurantClient = restaurantClient;
	}

	public Order createOrder(Order order) {
		restaurantClient.getRestaurant(order.getRestaurantId());
		order.setStatus("CREATED");
		return orderRepository.save(order);
	}
}
