package com.managment.presentialmanagment.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.managment.presentialmanagment.domain.Team;
import com.managment.presentialmanagment.dto.TeamDTO;
import com.managment.presentialmanagment.services.TeamService;

@RestController
@RequestMapping(value="/teams")
public class TeamResource {
	
	@Autowired
	private TeamService service;
	
	@RequestMapping(value = "/{id}", method= RequestMethod.GET)
	public ResponseEntity<Team> find(@PathVariable Integer id ) {
		
		Team Team = service.find(id);
	
		return ResponseEntity.ok().body(Team);
	}
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody Team obj){
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody Team obj, @PathVariable Integer id){
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
	public ResponseEntity<List<TeamDTO>> findAll() {
		
		List<Team> list = service.findAll();
		List<TeamDTO> listDto  = list.stream().map(obj -> new TeamDTO(obj)).collect(Collectors.toList());
	
		return ResponseEntity.ok().body(listDto);
	}
}
