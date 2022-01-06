package com.managment.presentialmanagment.resources;

import java.net.URI;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.managment.presentialmanagment.domain.Team;
import com.managment.presentialmanagment.domain.Topic;
import com.managment.presentialmanagment.dto.TeamDTO;
import com.managment.presentialmanagment.services.TeamService;
import com.managment.presentialmanagment.services.TopicService;

@RestController
@RequestMapping(value="/teams")
public class TeamResource {
	
	@Autowired
	private TeamService service;
	
	@Autowired
	private TopicService topicService;
	
	@RequestMapping(value = "/{id}", method= RequestMethod.GET)
	public ResponseEntity<Team> find(@PathVariable Integer id ) {
		
		Team Team = service.find(id);
	
		return ResponseEntity.ok().body(Team);
	}
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insertnewTeam(@Valid @RequestBody TeamDTO objDto){
		
		Team obj = service.fromDTO(objDto);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody Topic obj, @PathVariable Integer id){
		Team team = service.find(id);
		obj.setTeam(team);
		
		obj = topicService.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody TeamDTO objDto, @PathVariable Integer id){
		
		Team obj = service.fromDTO(objDto);
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
//	@RequestMapping(value = "/{id}/{id}", method = RequestMethod.DELETE)
//	public ResponseEntity<Void> deleteTopic(@PathVariable Integer idTeam, @PathVariable Integer idTopic){
//		Team team = service.find(idTeam);
//		
//		topicService.delete(idTopic);
//		
//		return ResponseEntity.noContent().build();
//	}
	
	@RequestMapping(method= RequestMethod.GET)
	public ResponseEntity<List<TeamDTO>> findAll() {
		
		List<Team> list = service.findAll();
		List<TeamDTO> listDto  = list.stream().map(obj -> new TeamDTO(obj)).collect(Collectors.toList());
	
		return ResponseEntity.ok().body(listDto);
	}
	
	@RequestMapping(value = "/page", method= RequestMethod.GET)
	public ResponseEntity<Page<TeamDTO>> findPage(
			@RequestParam(value = "page",  defaultValue = "0") Integer page, 
			@RequestParam(value = "linesPerPage",  defaultValue = "24") Integer linesPerPage, 
			@RequestParam(value = "orderBy",  defaultValue = "name") String orderBy, 
			@RequestParam(value = "direction",  defaultValue = "ASC") String direction) {
		
		Page<Team> list = service.findPage(page, linesPerPage, orderBy, direction);
		
		Page<TeamDTO> listDto  = list.map(obj -> new TeamDTO(obj));
		
		return ResponseEntity.ok().body(listDto);
	}
	
	@RequestMapping(value = "/{id}/page", method= RequestMethod.GET)
	public ResponseEntity<Page<Topic>> findPage(
			@PathVariable Integer id,
			@RequestParam(value = "model", defaultValue = "") String model,
			@RequestParam(value = "page",  defaultValue = "0") Integer page, 
			@RequestParam(value = "linesPerPage",  defaultValue = "24") Integer linesPerPage, 
			@RequestParam(value = "orderBy",  defaultValue = "initialDateUsage") String orderBy, 
			@RequestParam(value = "direction",  defaultValue = "ASC") String direction) {
		
		Page<Topic> list = topicService.search(id,model, page,linesPerPage, orderBy, direction);
		
		return ResponseEntity.ok().body(list);
	}
}
