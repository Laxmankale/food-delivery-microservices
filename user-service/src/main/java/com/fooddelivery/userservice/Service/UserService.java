package com.fooddelivery.userservice.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fooddelivery.userservice.Repository.UserRepository;
import com.fooddelivery.userservice.entity.User;

@Service
public class UserService {

	private final UserRepository repository;

	public UserService(UserRepository repository) {
		super();
		this.repository = repository;
	}

	public User registerUser(User user) {
		return repository.save(user);
	}

	public User getUserById(Long id) {
		return repository.findById(id)
				.orElseThrow(() -> new RuntimeException("User not found with id: " + id));
	}

	public List<User> getAllUsers() {
		return repository.findAll();
	}

	public User updateUser(Long id, User userDetails) {
		User user = repository.findById(id)
				.orElseThrow(() -> new RuntimeException("User not found with id: " + id));
		user.setName(userDetails.getName());
		user.setEmail(userDetails.getEmail());
		user.setPassword(userDetails.getPassword());
		return repository.save(user);
	}

	public void deleteUser(Long id) {
		User user = repository.findById(id)
				.orElseThrow(() -> new RuntimeException("User not found with id: " + id));
		repository.delete(user);
	}
}
