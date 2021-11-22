package com.managment.presentialmanagment.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.managment.presentialmanagment.domain.User;
import com.managment.presentialmanagment.services.UserService;

@RestController
@RequestMapping(value="/users")
public class UserResource {
	
	@Autowired
	private UserService service;
	
	@RequestMapping(method= RequestMethod.GET)
	public List<User> getAll() {
		User user = new User(1, "Cláudia", "claudia@hotmail.com", "123");
		User user2 = new User(2, "Eric", "erica@hotmail.com", "123");
		
		List<User> users = new ArrayList<>();
		users.add(user);
		users.add(user2);
		
		return users;
	}
	@RequestMapping(value = "/{id}", method= RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id ) {
		
		User user = service.find(id);
	
		return ResponseEntity.ok().body(user);
	}
}
