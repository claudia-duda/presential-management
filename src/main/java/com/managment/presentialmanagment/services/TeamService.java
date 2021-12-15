package com.managment.presentialmanagment.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.managment.presentialmanagment.domain.Team;
import com.managment.presentialmanagment.repositories.TeamRepository;
import com.managment.presentialmanagment.services.exceptions.DataIntegrityException;
import com.managment.presentialmanagment.services.exceptions.ObjectNotFoundException;

@Service
public class TeamService {
	
	@Autowired
	private TeamRepository repository;
	//TODO would be a good idea implements a generic service?
	public Team find(Integer id) { 
		Optional<Team> obj = repository.findById(id); 
		return obj.orElseThrow(() ->  new ObjectNotFoundException(
				"Object not found! Id: "+ id +", Type: " + Team.class.getName()));

	} 
	
	public Team insert(Team obj) {
		obj.setId(null);
		return repository.save(obj);
		
	}
	
	public Team update(Team obj) {
		find(obj.getId());
		return repository.save(obj);
		
	}
	
	public void delete(Integer id) {
		find(id);
		try{
			repository.deleteById(id);
		}catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Its no possible delete a Team who is into a topic");
		}
	}
	
	public List<Team> findAll(){
		return repository.findAll();
	}
	
}
