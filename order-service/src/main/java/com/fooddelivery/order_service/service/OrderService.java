package com.fooddelivery.order_service.service;

import java.util.List;

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

	public Order getOrderById(Long id) {
		return orderRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Order not found with id: " + id));
	}

	public List<Order> getAllOrders() {
		return orderRepository.findAll();
	}

	public Order updateOrderStatus(Long id, String status) {
		Order order = orderRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Order not found with id: " + id));
		order.setStatus(status);
		return orderRepository.save(order);
	}

	public void cancelOrder(Long id) {
		Order order = orderRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Order not found with id: " + id));
		order.setStatus("CANCELLED");
		orderRepository.save(order);
	}
}
