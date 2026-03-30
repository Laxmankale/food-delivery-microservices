package com.fooddelivery.order_service.service;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.fooddelivery.order_service.client.RestaurantClient;
import com.fooddelivery.order_service.entity.Order;
import com.fooddelivery.order_service.event.OrderCreatedEvent;
import com.fooddelivery.order_service.exception.InvalidOrderStatusException;
import com.fooddelivery.order_service.exception.OrderNotFoundException;
import com.fooddelivery.order_service.exception.RestaurantServiceException;
import com.fooddelivery.order_service.kafka.OrderProducer;
import com.fooddelivery.order_service.repository.OrderRepository;

@Service
public class OrderService {

	private static final Set<String> VALID_STATUSES = Set.of(
			"CREATED", "CONFIRMED", "PREPARING", "OUT_FOR_DELIVERY", "DELIVERED", "CANCELLED");

	private final OrderRepository orderRepository;
	private final RestaurantClient restaurantClient;
	private final OrderProducer producer;

	public OrderService(OrderRepository orderRepository, RestaurantClient restaurantClient, OrderProducer producer) {
		this.orderRepository = orderRepository;
		this.restaurantClient = restaurantClient;
		this.producer = producer;
	}

	public Order createOrder(Order order) {
		try {
			restaurantClient.getRestaurant(order.getRestaurantId());
		} catch (RestaurantServiceException ex) {
			throw ex;
		} catch (Exception ex) {
			throw new RestaurantServiceException("Failed to verify restaurant", ex);
		}
		order.setStatus("CREATED");
		Order saved = orderRepository.save(order);
		OrderCreatedEvent event = new OrderCreatedEvent(saved.getId(), saved.getUserId(), saved.getRestaurantId());
		producer.sendOrderCreatedEvent(event);
		return saved;
	}

	public Order getOrderById(Long id) {
		return orderRepository.findById(id)
				.orElseThrow(() -> new OrderNotFoundException(id));
	}

	public List<Order> getAllOrders() {
		return orderRepository.findAll();
	}

	public Order updateOrderStatus(Long id, String status) {
		if (status == null || !VALID_STATUSES.contains(status.toUpperCase())) {
			throw new InvalidOrderStatusException(status);
		}
		Order order = orderRepository.findById(id)
				.orElseThrow(() -> new OrderNotFoundException(id));
		order.setStatus(status.toUpperCase());
		return orderRepository.save(order);
	}

	public void cancelOrder(Long id) {
		Order order = orderRepository.findById(id)
				.orElseThrow(() -> new OrderNotFoundException(id));
		order.setStatus("CANCELLED");
		orderRepository.save(order);
	}
}
