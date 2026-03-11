package com.fooddelivery.order_service.event;

public class OrderCreatedEvent {

	private Long orderId;
	private Long userId;
	private Long restaurantId;

	public OrderCreatedEvent(Long orderId, Long userId, Long restaurantId) {
		this.orderId = orderId;
		this.userId = userId;
		this.restaurantId = restaurantId;
	}

	public Long getOrderId() {
		return orderId;
	}

	public Long getUserId() {
		return userId;
	}

	public Long getRestaurantId() {
		return restaurantId;
	}
}
