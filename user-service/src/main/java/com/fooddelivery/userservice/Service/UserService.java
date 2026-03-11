package com.fooddelivery.userservice.Service;

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
}
