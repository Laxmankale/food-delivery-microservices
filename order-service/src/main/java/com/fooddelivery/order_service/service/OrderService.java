package com.fooddelivery.order_service.service;

import org.springframework.stereotype.Service;

import com.fooddelivery.order_service.client.RestaurantClient;
import com.fooddelivery.order_service.entity.Order;
import com.fooddelivery.order_service.event.OrderCreatedEvent;
import com.fooddelivery.order_service.kafka.OrderProducer;
import com.fooddelivery.order_service.repository.OrderRepository;

@Service
public class OrderService {

	private final OrderRepository orderRepository;
	private final RestaurantClient restaurantClient;
	private final OrderProducer producer;

	public OrderService(OrderRepository orderRepository, RestaurantClient restaurantClient, OrderProducer producer) {
		super();
		this.orderRepository = orderRepository;
		this.restaurantClient = restaurantClient;
		this.producer = producer;
	}

	public Order createOrder(Order order) {
		restaurantClient.getRestaurant(order.getRestaurantId());
		order.setStatus("CREATED");
		Order saved = orderRepository.save(order);
		OrderCreatedEvent event = new OrderCreatedEvent(saved.getId(), saved.getUserId(), saved.getRestaurantId());
		producer.sendOrderCreatedEvent(event);
		return saved;
	}
}
