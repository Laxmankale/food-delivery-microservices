package com.fooddelivery.userservice.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

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

	@GetMapping("/{id}")
	public User getUserById(@PathVariable Long id) {
		return service.getUserById(id);
	}

	@GetMapping
	public List<User> getAllUsers() {
		return service.getAllUsers();
	}

	@PutMapping("/{id}")
	public User updateUser(@PathVariable Long id, @RequestBody User user) {
		return service.updateUser(id, user);
	}

	@DeleteMapping("/{id}")
	public String deleteUser(@PathVariable Long id) {
		service.deleteUser(id);
		return "User deleted successfully";
	}
}
