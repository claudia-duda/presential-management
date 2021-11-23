package com.managment.presentialmanagment.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.managment.presentialmanagment.domain.User;
import com.managment.presentialmanagment.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repository;
	
	public User find(Integer id) { 
		Optional<User> obj = repository.findById(id); 
		return obj.orElse(null); 
	} 
	
}
