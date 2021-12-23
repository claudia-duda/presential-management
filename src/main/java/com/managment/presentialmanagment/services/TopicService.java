package com.managment.presentialmanagment.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.managment.presentialmanagment.domain.Topic;
import com.managment.presentialmanagment.repositories.TopicRepository;
//TODO comunication between services(team and topics)
@Service
public class TopicService {
	@Autowired
	private TopicRepository repository;
	
	public Page<Topic> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repository.findAll(pageRequest);
	}
}
