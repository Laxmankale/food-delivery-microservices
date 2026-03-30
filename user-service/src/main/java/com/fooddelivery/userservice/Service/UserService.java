package com.fooddelivery.userservice.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fooddelivery.userservice.Repository.UserRepository;
import com.fooddelivery.userservice.entity.User;
import com.fooddelivery.userservice.exception.DuplicateEmailException;
import com.fooddelivery.userservice.exception.UserNotFoundException;

@Service
public class UserService {

	private final UserRepository repository;

	public UserService(UserRepository repository) {
		this.repository = repository;
	}

	public User registerUser(User user) {
		repository.findByEmail(user.getEmail()).ifPresent(existing -> {
			throw new DuplicateEmailException(user.getEmail());
		});
		return repository.save(user);
	}

	public User getUserById(Long id) {
		return repository.findById(id)
				.orElseThrow(() -> new UserNotFoundException(id));
	}

	public List<User> getAllUsers() {
		return repository.findAll();
	}

	public User updateUser(Long id, User userDetails) {
		User user = repository.findById(id)
				.orElseThrow(() -> new UserNotFoundException(id));
		user.setName(userDetails.getName());
		user.setEmail(userDetails.getEmail());
		user.setPassword(userDetails.getPassword());
		return repository.save(user);
	}

	public void deleteUser(Long id) {
		User user = repository.findById(id)
				.orElseThrow(() -> new UserNotFoundException(id));
		repository.delete(user);
	}
}
