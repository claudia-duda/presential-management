package com.managment.presentialmanagment.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.managment.presentialmanagment.domain.Request;
import com.managment.presentialmanagment.domain.Request;
import com.managment.presentialmanagment.services.RequestService;

@RestController
@RequestMapping(value="/requests")
public class RequestResource {
	
	@Autowired
	private RequestService service;
	
	@RequestMapping(value = "/{id}", method= RequestMethod.GET)
	public ResponseEntity<Request> find(@PathVariable Integer id ) {
		
		Request Request = service.find(id);
	
		return ResponseEntity.ok().body(Request);
	}
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody Request obj){
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody Request obj, @PathVariable Integer id){
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(method= RequestMethod.GET)
	public ResponseEntity<List<Request>> findAll() {
		
		List<Request> list = service.findAll();
	
		return ResponseEntity.ok().body(list);
	}
}
