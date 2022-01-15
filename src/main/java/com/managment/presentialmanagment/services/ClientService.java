package com.managment.presentialmanagment.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.managment.presentialmanagment.domain.Team;
import com.managment.presentialmanagment.domain.enums.Profile;
import com.managment.presentialmanagment.domain.Client;
import com.managment.presentialmanagment.dto.ClientDTO;
import com.managment.presentialmanagment.dto.ClientNewDTO;
import com.managment.presentialmanagment.repositories.ClientRepository;
import com.managment.presentialmanagment.security.UserSS;
import com.managment.presentialmanagment.services.exceptions.AuthorizationException;
import com.managment.presentialmanagment.services.exceptions.DataIntegrityException;
import com.managment.presentialmanagment.services.exceptions.ObjectNotFoundException;

@Service
public class ClientService {
	
	@Autowired
	private ClientRepository repository;
	
	@Autowired
	private TeamService teamService;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	//TODO would be a good idea implements a generic service?
	public Client find(Integer id) { 
		UserSS user = UserService.authenticated();
		
		if(user == null || !user.hasRole(Profile.ADMIN) && !id.equals(user.getId())) {
			throw new AuthorizationException("You don't have permission to see others Users");
		}
		
		Optional<Client> obj = repository.findById(id); 
		return obj.orElseThrow(() ->  new ObjectNotFoundException(
				"Object not found! Id: "+ id +", Type: " + Client.class.getName()));
	} 
	@Transactional
	public Client insert(Client obj) {
		obj.setId(null);
		
		return repository.save(obj);
		
	}

	public Client update(Client obj) {
		Client newObj = find(obj.getId());
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
	
	public List<Client> findAll(){
		return repository.findAll();
	}
	
	public Page<Client> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repository.findAll(pageRequest);
	}
	
	public Client fromDTO(ClientDTO objDTO) {
		return new Client(objDTO.getId(),objDTO.getName(),objDTO.getEmail(),null, null);
	}
	
	public Client fromDTO(ClientNewDTO objDTO) {
		Team team = teamService.find(objDTO.getTeamId());
		Client newUser = new Client(null, objDTO.getName(), objDTO.getEmail(),
				passwordEncoder.encode(objDTO.getPassword()), team);
		
		team.addUsers(newUser);
		teamService.update(team);
		
		return newUser;
	}

	private void updateData(Client newObj, Client obj) {
		newObj.setEmail(obj.getEmail());
		newObj.setName(obj.getName());
		
	}
}
