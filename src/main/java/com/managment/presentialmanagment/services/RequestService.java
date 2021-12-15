package com.managment.presentialmanagment.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.managment.presentialmanagment.domain.Request;
import com.managment.presentialmanagment.repositories.RequestRepository;
import com.managment.presentialmanagment.services.exceptions.DataIntegrityException;
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
	public Request insert(Request obj) {
		obj.setId(null);
		return repository.save(obj);
		
	}
	
	public Request update(Request obj) {
		find(obj.getId());
		return repository.save(obj);
		
	}
	
	public void delete(Integer id) {
		find(id);
		try{
			repository.deleteById(id);
		}catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Its no possible delete a Request who is into a topic");
		}
	}
	
	public List<Request> findAll(){
		return repository.findAll();
	}
	
}
