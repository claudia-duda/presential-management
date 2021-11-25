package com.managment.presentialmanagment.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.managment.presentialmanagment.domain.Cellphone;
import com.managment.presentialmanagment.repositories.CellphoneRepository;
import com.managment.presentialmanagment.services.exceptions.ObjectNotFoundException;

@Service
public class CellphoneService {
	
	@Autowired
	private CellphoneRepository repository;
	//TODO would be a good idea implements a generic service?
	public Cellphone find(Integer id) { 
		Optional<Cellphone> obj = repository.findById(id); 
		return obj.orElseThrow(() ->  new ObjectNotFoundException(
				"Object not found! Id: "+ id +", Type: " + Cellphone.class.getName()));
	
	
	} 
	
}
