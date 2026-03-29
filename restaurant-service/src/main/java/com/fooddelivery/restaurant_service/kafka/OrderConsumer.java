package com.fooddelivery.restaurant_service.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.fooddelivery.restaurant_service.event.OrderCreatedEvent;

@Service
public class OrderConsumer {

	private static final Logger log = LoggerFactory.getLogger(OrderConsumer.class);

	@KafkaListener(topics = "order-created", groupId = "restaurant-group")
	public void handleOrderCreated(OrderCreatedEvent event) {
		log.info("Received order event — orderId: {}, restaurantId: {}, userId: {}",
				event.getOrderId(), event.getRestaurantId(), event.getUserId());

	}
}
