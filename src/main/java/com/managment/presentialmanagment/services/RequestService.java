package com.managment.presentialmanagment.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.managment.presentialmanagment.domain.Request;
import com.managment.presentialmanagment.repositories.RequestRepository;
import com.managment.presentialmanagment.services.exceptions.ObjectNotFoundException;

@Service
public class RequestService {
	
	@Autowired
	private RequestRepository repository;
	//TODO would be a good idea implements a generic service?
	public Request find(Integer id) { 
		Optional<Request> obj = repository.findById(id); 
		return obj.orElseThrow(() ->  new ObjectNotFoundException(
				"Object not found! Id: "+ id +", Type: " + Request.class.getName()));
	
	} 
	
}
