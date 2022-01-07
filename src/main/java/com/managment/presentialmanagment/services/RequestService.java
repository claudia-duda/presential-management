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

import com.managment.presentialmanagment.domain.Request;
import com.managment.presentialmanagment.domain.enums.PriorityEnum;
import com.managment.presentialmanagment.domain.enums.StateEnum;
import com.managment.presentialmanagment.repositories.RequestRepository;
import com.managment.presentialmanagment.services.exceptions.DataIntegrityException;
import com.managment.presentialmanagment.services.exceptions.ObjectNotFoundException;

@Service
public class RequestService {
	
	@Autowired
	private RequestRepository repository;
	
	@Autowired
	private CellphoneService cellphoneService;
	
	@Autowired
	private ClientService clientService;
	
	@Autowired
	private EmailService emailService;
	
	//TODO would be a good idea implements a generic service?
	
	//TODO find and delete needs to be implemented
	public Request find(Integer id) { 
		Optional<Request> obj = repository.findById(id); 
		return obj.orElseThrow(() ->  new ObjectNotFoundException(
				"Object not found! Id: "+ id +", Type: " + Request.class.getName()));
	
	} 
	
	//TODO would be a good idea insert a cellphone into request only using the id? and throw a exception if it wasn't create yet
	// or let the user put the whole object and if it doesn't exist
	@Transactional
	public Request insert(Request obj) {
		
		//Cellphone cellphone = obj.getCellphone();
		
		//if(cellphone.getId() == null) {
		//	cellphoneService.insert(cellphone);
		//}
		obj.setCellphone(cellphoneService.find(obj.getCellphone().getId()));
		
		obj.setDate(LocalDateTime.now());
		
		if(obj.getPriority() == null) {
			obj.setPriority(PriorityEnum.GREEN);
		}
		obj.setState(StateEnum.PENDING);
		obj.setUser(clientService.find(obj.getUser().getId()));
		obj = repository.save(obj);
		emailService.sendRequestWaiting(obj);
		return obj;
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
	public Page<Request> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repository.findAll(pageRequest);
	}
	
}
