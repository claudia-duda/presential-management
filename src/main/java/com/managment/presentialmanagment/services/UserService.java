package com.managment.presentialmanagment.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.managment.presentialmanagment.domain.User;
import com.managment.presentialmanagment.repositories.UserRepository;
import com.managment.presentialmanagment.services.exceptions.ObjectNotFoundException;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repository;
	//TODO would be a good idea implements a generic service?
	public User find(Integer id) { 
		Optional<User> obj = repository.findById(id); 
		return obj.orElseThrow(() ->  new ObjectNotFoundException(
				"Object not found! Id: "+ id +", Type: " + User.class.getName()));
	
	
	} 
	
}
