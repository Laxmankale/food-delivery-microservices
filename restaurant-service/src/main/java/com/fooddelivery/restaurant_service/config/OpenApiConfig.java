package com.fooddelivery.restaurant_service.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

	@Bean
	public OpenAPI restaurantServiceOpenAPI() {
		return new OpenAPI()
				.info(new Info()
						.title("Restaurant Service API")
						.description("API for restaurant management")
						.version("1.0"));
	}
}
