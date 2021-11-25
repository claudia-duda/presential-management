package com.managment.presentialmanagment.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.managment.presentialmanagment.domain.Cellphone;
import com.managment.presentialmanagment.services.CellphoneService;

@RestController
@RequestMapping(value="/cellphones")
public class CellphoneResource {
	
	@Autowired
	private CellphoneService service;
	
	@RequestMapping(value = "/{id}", method= RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id ) {
		
		Cellphone Cellphone = service.find(id);
	
		return ResponseEntity.ok().body(Cellphone);
	}
}
