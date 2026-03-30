package com.fooddelivery.order_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fooddelivery.order_service.exception.RestaurantServiceException;

import feign.Response;
import feign.codec.ErrorDecoder;

@Configuration
public class FeignErrorDecoder {

	@Bean
	public ErrorDecoder errorDecoder() {
		return new CustomErrorDecoder();
	}

	private static class CustomErrorDecoder implements ErrorDecoder {

		private final ErrorDecoder defaultDecoder = new ErrorDecoder.Default();

		@Override
		public Exception decode(String methodKey, Response response) {
			if (response.status() == 404) {
				return new RestaurantServiceException("Restaurant not found for the given ID");
			}
			if (response.status() >= 500) {
				return new RestaurantServiceException("Restaurant service is currently unavailable");
			}
			return defaultDecoder.decode(methodKey, response);
		}
	}
}
