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
import com.managment.presentialmanagment.domain.Client;
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
	
	
	@Transactional
	public Request insert(Request obj) {
		

		Cellphone cellphone = cellphoneService.find(obj.getCellphone().getId());
		Client client = clientService.find(obj.getClient().getId());
		obj.setDate(LocalDateTime.now());
		
		if(obj.getPriority() == null) {
			obj.setPriority(PriorityEnum.GREEN);
		}
		obj.setState(StateEnum.PENDING);
		
		client.addRequest(obj);
		cellphone.addRequest(obj);
		
		obj = repository.save(obj);
		
		emailService.sendRequestWaitingHtmlEmail(obj);
		return obj;
	}
	
	public void delete(Request obj) {
		try{
			Client client = clientService.find(obj.getClient().getId());

			Cellphone cellphone = cellphoneService.find(obj.getCellphone().getId());
			if(client.removeRequest(obj) && cellphone.removeRequest(obj)) {
				
				repository.delete(obj);
			}else {
				throw new ObjectNotFoundException("Request not found yet");
			}
			
			
		}catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Problems into their relationships");
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
