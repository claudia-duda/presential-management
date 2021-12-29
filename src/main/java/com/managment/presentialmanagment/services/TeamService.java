package com.managment.presentialmanagment.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.managment.presentialmanagment.domain.Team;
import com.managment.presentialmanagment.dto.TeamDTO;
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
		Team newObj = find(obj.getId());
		updateData(newObj, obj);
		return repository.save(newObj);
		
	}
	
	public void delete(Integer id) {
		find(id);
		try{
			repository.deleteById(id);
		}catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Its no possible delete a Team who has topics");
		}
	}
	
	public List<Team> findAll(){
		return repository.findAll();
	}
	
	public Page<Team> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repository.findAll(pageRequest);
	}
	
	public Team fromDTO(TeamDTO objDTO) {
		return new Team(objDTO.getId(),objDTO.getName());
	}
	
	private void updateData(Team newObj, Team obj) {
		newObj.setName(obj.getName());
		
	}
	
}
