package com.managment.presentialmanagment.services;

import java.time.LocalDateTime;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.managment.presentialmanagment.domain.Cellphone;
import com.managment.presentialmanagment.domain.Request;
import com.managment.presentialmanagment.domain.Team;
import com.managment.presentialmanagment.domain.Topic;
import com.managment.presentialmanagment.domain.Client;
import com.managment.presentialmanagment.domain.enums.CategoryEnum;
import com.managment.presentialmanagment.domain.enums.PriorityEnum;
import com.managment.presentialmanagment.domain.enums.StateEnum;
import com.managment.presentialmanagment.repositories.CellphoneRepository;
import com.managment.presentialmanagment.repositories.RequestRepository;
import com.managment.presentialmanagment.repositories.TeamRepository;
import com.managment.presentialmanagment.repositories.TopicRepository;
import com.managment.presentialmanagment.repositories.ClientRepository;

@Service
public class DBService {
	
	@Autowired
	private ClientRepository userRepository;
	@Autowired
	private RequestRepository requestRepository;
	@Autowired
	private CellphoneRepository cellphoneRepository;
	@Autowired
	private TeamRepository teamRepository;
	@Autowired
	private TopicRepository topicRepository;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	public void instantiateTestDatabase() {
		Team team = new Team(null, "manual");
		Team team2 = new Team(null, "pre-cert");
		Team team3 = new Team(null, "CBS");

		Client user = new Client(null, "Cláudia", "cl.dudaramons@gmail.com", passwordEncoder.encode("123"), team);
		Client user2 = new Client(null, "Eric", "erica@hotmail.com", passwordEncoder.encode("123"), team);
		Client user3 = new Client(null, "camila", "camila@hotmail.com", passwordEncoder.encode("123"), team2);

		Cellphone cellphone = new Cellphone(null, "cebu", "pvt", "CEBU-03", "123456789", "987456321");
		Cellphone cellphone2 = new Cellphone(null, "tahoe", "evt", "TAHOE-01", "123456789", "987456321");
		Cellphone cellphone3 = new Cellphone(null, "milan", "evt", "TAHOE-16", "123456789", "987456321");
		Cellphone cellphone4 = new Cellphone(null, "berlin", "evt", "TAHOE-14", "123456789", "987456321");
		Cellphone cellphone5 = new Cellphone(null, "racer-turbo", "evt", "TAHOE-85", "123456789", "987456321");
		Cellphone cellphone6 = new Cellphone(null, "cat2", "evt", "TAHOE-74", "123456789", "987456321");
		Cellphone cellphone7 = new Cellphone(null, "omde", "evt", "TAHOE-98", "123456789", "987456321");

		
		Request request = new Request(user, cellphone, PriorityEnum.RED.getCode(), LocalDateTime.now().plusMinutes(20),
				CategoryEnum.HEADSET.getCode(), StateEnum.PENDING.getCode(), null, null);

		Request request2 = new Request(user2, cellphone2, PriorityEnum.YELLOW.getCode(),
				LocalDateTime.now().plusMinutes(10), CategoryEnum.HEADSET.getCode(), 
				StateEnum.PENDING.getCode(), null, null);

		Request request3 = new Request(user3, cellphone2, PriorityEnum.GREEN.getCode(), LocalDateTime.now(),
				CategoryEnum.HEADSET.getCode(), StateEnum.PENDING.getCode(), null, null);

//		user2.getRequests().addAll(Arrays.asList(request2));
//		user3.getRequests().addAll(Arrays.asList(request3));
//		user.getRequests().addAll(Arrays.asList(request));

		Topic topic = new Topic(cellphone2, team2, LocalDateTime.now(), LocalDateTime.now().plusDays(3));
		Topic topic2 = new Topic(cellphone, team, LocalDateTime.now(), LocalDateTime.now().plusDays(3));
		Topic topic3 = new Topic(cellphone5, team, LocalDateTime.now(), LocalDateTime.now().plusDays(3));
		Topic topic4 = new Topic(cellphone6, team2, LocalDateTime.now(), LocalDateTime.now().plusDays(3));

		cellphone.getTopics().addAll(Arrays.asList(topic2));
		cellphone2.getTopics().addAll(Arrays.asList(topic));

		team.getTopics().addAll(Arrays.asList(topic2));
		team2.getTopics().addAll(Arrays.asList(topic));

		teamRepository.saveAll(Arrays.asList(team, team2, team3));
		userRepository.saveAll(Arrays.asList(user, user2, user3));
		cellphoneRepository.saveAll(
				Arrays.asList(cellphone, cellphone2, cellphone3, cellphone4, cellphone5, cellphone6, cellphone7));
		topicRepository.saveAll(Arrays.asList(topic, topic2, topic3, topic4));
		requestRepository.saveAll(Arrays.asList(request, request2, request3));
	}
}
