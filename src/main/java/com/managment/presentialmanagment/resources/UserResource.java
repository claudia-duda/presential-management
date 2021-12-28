package com.managment.presentialmanagment.resources;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.managment.presentialmanagment.domain.User;
import com.managment.presentialmanagment.dto.UserDTO;
import com.managment.presentialmanagment.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

	@Autowired
	private UserService service;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<User> find(@PathVariable Integer id) {

		User user = service.find(id);

		return ResponseEntity.ok().body(user);
	}

//	@RequestMapping(method = RequestMethod.POST)
//	public ResponseEntity<Void> insert(@RequestBody User obj) {
//		obj = service.insert(obj);
//		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
//		return ResponseEntity.created(uri).build();
//	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody UserDTO objDto, @PathVariable Integer id){
		User obj = service.fromDTO(objDto);
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
	public ResponseEntity<List<UserDTO>> findAll() {
		
		List<User> list = service.findAll();
		List<UserDTO> listDto  = list.stream().map(obj -> new UserDTO(obj)).collect(Collectors.toList());
	
		return ResponseEntity.ok().body(listDto);
	}
	
	@RequestMapping(value = "/page", method= RequestMethod.GET)
	public ResponseEntity<Page<UserDTO>> findPage(
			@RequestParam(value = "page",  defaultValue = "0") Integer page, 
			@RequestParam(value = "linesPerPage",  defaultValue = "24") Integer linesPerPage, 
			@RequestParam(value = "orderBy",  defaultValue = "name") String orderBy, 
			@RequestParam(value = "direction",  defaultValue = "ASC") String direction) {
		
		Page<User> list = service.findPage(page, linesPerPage, orderBy, direction);
		
		Page<UserDTO> listDto  = list.map(obj -> new UserDTO(obj));
		
		return ResponseEntity.ok().body(listDto);
	}
}
