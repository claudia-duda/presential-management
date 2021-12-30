package com.managment.presentialmanagment.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.managment.presentialmanagment.domain.Team;
import com.managment.presentialmanagment.domain.User;
import com.managment.presentialmanagment.dto.UserDTO;
import com.managment.presentialmanagment.dto.UserNewDTO;
import com.managment.presentialmanagment.repositories.UserRepository;
import com.managment.presentialmanagment.services.exceptions.DataIntegrityException;
import com.managment.presentialmanagment.services.exceptions.ObjectNotFoundException;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private TeamService teamService;
	//TODO would be a good idea implements a generic service?
	public User find(Integer id) { 
		Optional<User> obj = repository.findById(id); 
		return obj.orElseThrow(() ->  new ObjectNotFoundException(
				"Object not found! Id: "+ id +", Type: " + User.class.getName()));
	
	
	} 
	@Transactional
	public User insert(User obj) {
		obj.setId(null);
		
		User user = repository.findByEmail(obj.getEmail());
		
		if(user != null) {
			throw new DataIntegrityException("The email is already saved on database");
		}
		return repository.save(obj);
		
	}

	public User update(User obj) {
		User newObj = find(obj.getId());
		updateData(newObj, obj);
		return repository.save(newObj);
		
	}
	
	public void delete(Integer id) {
		find(id);
		try{
			repository.deleteById(id);
		}catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Its no possible delete a User who has requests");
		}
	}
	
	public List<User> findAll(){
		return repository.findAll();
	}
	
	public Page<User> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repository.findAll(pageRequest);
	}
	
	public User fromDTO(UserDTO objDTO) {
		return new User(objDTO.getId(),objDTO.getName(),objDTO.getEmail(),null, null);
	}
	
	public User fromDTO(UserNewDTO objDTO) {
		Team team = teamService.find(objDTO.getTeamId());
		User newUser = new User(null, objDTO.getName(), objDTO.getEmail(), objDTO.getPassword(), team);
		
		team.addUsers(newUser);
		teamService.update(team);
		
		return newUser;
	}

	private void updateData(User newObj, User obj) {
		newObj.setEmail(obj.getEmail());
		newObj.setName(obj.getName());
		
	}
}
