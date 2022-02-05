package com.managment.presentialmanagment.services;

import java.time.LocalDateTime;
import java.util.List;

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
import com.managment.presentialmanagment.repositories.TopicRepository;
import com.managment.presentialmanagment.services.exceptions.DataIntegrityException;
import com.managment.presentialmanagment.services.exceptions.ObjectNotFoundException;

@Service
public class TopicService {
	@Autowired
	private TopicRepository repository;
	
	@Autowired
	private CellphoneService cellphoneService;
	
	@Autowired
	private TeamService teamService;
	
	
	@Transactional
	public Topic insert(Topic obj) {
		
		Cellphone cellphone = obj.getCellphone();
		obj.setInitialDateUsage(LocalDateTime.now());
		
		cellphone.addTopic(obj);
		Team team = teamService.find(obj.getTeam().getId());
		team.addTopic(obj);	
		
		obj = repository.save(obj);
		return obj;
	}
	
	public void delete(Topic obj) {
		try{
			Team team = teamService.find(obj.getTeam().getId());

			Cellphone cellphone = cellphoneService.find(obj.getCellphone().getId());
			if(team.removeTopic(obj) && cellphone.removeTopic(obj)) {
				
				repository.delete(obj);
			}else {
				throw new ObjectNotFoundException("Topic not found");
			}
			
			
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
