package com.managment.presentialmanagment.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.managment.presentialmanagment.domain.Cellphone;
import com.managment.presentialmanagment.domain.Team;
import com.managment.presentialmanagment.domain.Topic;
import com.managment.presentialmanagment.repositories.CellphoneRepository;
import com.managment.presentialmanagment.repositories.TopicRepository;
import com.managment.presentialmanagment.services.exceptions.DataIntegrityException;
import com.managment.presentialmanagment.services.exceptions.ObjectNotFoundException;

@Service
public class TopicService {
	@Autowired
	private TopicRepository repository;
	
	@Autowired
	private CellphoneRepository cellphoneRepository;
	
	@Autowired
	private TeamService teamService;
	//TODO find and delete needs to be implemented
	public Topic find(Integer id) { 
		Optional<Topic> obj = repository.findById(id); 
		return obj.orElseThrow(() ->  new ObjectNotFoundException(
				"Object not found! Id: "+ id +", Type: " + Topic.class.getName()));
	
	} 
	
	@Transactional
	public Topic insert(Topic obj) {
		
		Cellphone cellphone = obj.getCellphone();
		cellphone.addTopic(obj);
		cellphoneRepository.save(cellphone);
		
		obj.setInitialDateUsage(LocalDateTime.now());
		
		Team team = teamService.find(obj.getTeam().getId());
		team.addTopic(obj);
		
		obj = repository.save(obj);
		return obj;
	}
	
	public void delete(Integer id) {
		find(id);
		try{
			repository.deleteById(id);
		}catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Problems into Topic's relationships");
		}
	}
	public Page<Topic> search(Integer teamId, String model, Integer page, Integer linesPerPage, 
			String orderBy, String direction){ 
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		
		Team team = teamService.find(teamId);
		List<Cellphone> cellphones = team.getCellphones();
		
		return repository.search(model,cellphones,pageRequest);
		
	}
	
}
