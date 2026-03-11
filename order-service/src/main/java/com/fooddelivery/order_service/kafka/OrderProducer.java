package com.fooddelivery.order_service.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.fooddelivery.order_service.event.OrderCreatedEvent;

@Service
public class OrderProducer {

	private final KafkaTemplate<String, Object> kafkaTemplate;

	public OrderProducer(KafkaTemplate<String, Object> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}

	public void sendOrderCreatedEvent(OrderCreatedEvent event) {
		kafkaTemplate.send("order-created", event);
	}
}