package com.managment.presentialmanagment.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.managment.presentialmanagment.domain.Cellphone;
import com.managment.presentialmanagment.repositories.CellphoneRepository;
import com.managment.presentialmanagment.services.exceptions.DataIntegrityException;
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
	
	public Cellphone insert(Cellphone obj) {
		Cellphone cellphone = repository.findByCode(obj.getCode());

		if(cellphone != null) {
			throw new DataIntegrityException("The cellphone's code is already saved on database");
		}
		obj.setId(null);
		return repository.save(obj);
		
	}
	
	public Cellphone update(Cellphone obj) {
		find(obj.getId());
		return repository.save(obj);
		
	}
	
	public void delete(Integer id) {
		find(id);
		try{
			repository.deleteById(id);
		}catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Its no possible delete a cellphone who is into a topic");
		}
	}
	
	public List<Cellphone> findAll(){
		return repository.findAll();
	}
	
	public Page<Cellphone> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repository.findAll(pageRequest);
	}
}
