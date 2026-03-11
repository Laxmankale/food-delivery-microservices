package com.fooddelivery.userservice.Controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fooddelivery.userservice.Service.UserService;
import com.fooddelivery.userservice.entity.User;

@RestController
@RequestMapping("/api/users")
public class UserController {

	private final UserService service;

	public UserController(UserService service) {
		super();
		this.service = service;
	}

	@PostMapping("/register")
	public User registerUser(@RequestBody User user) {
		return service.registerUser(user);
	}
}
