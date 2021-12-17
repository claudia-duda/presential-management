package com.managment.presentialmanagment;

import java.time.LocalDateTime;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.managment.presentialmanagment.domain.Cellphone;
import com.managment.presentialmanagment.domain.Request;
import com.managment.presentialmanagment.domain.Team;
import com.managment.presentialmanagment.domain.Topic;
import com.managment.presentialmanagment.domain.User;
import com.managment.presentialmanagment.domain.enums.CategoryEnum;
import com.managment.presentialmanagment.domain.enums.PriorityEnum;
import com.managment.presentialmanagment.repositories.CellphoneRepository;
import com.managment.presentialmanagment.repositories.RequestRepository;
import com.managment.presentialmanagment.repositories.TeamRepository;
import com.managment.presentialmanagment.repositories.TopicRepository;
import com.managment.presentialmanagment.repositories.UserRepository;

@SpringBootApplication
public class PresentialmanagmentApplication implements CommandLineRunner{
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RequestRepository requestRepository;
	@Autowired
	private CellphoneRepository cellphoneRepository;
	@Autowired
	private TeamRepository teamRepository;
	@Autowired
	private TopicRepository topicRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(PresentialmanagmentApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Team team = new Team(null,"manual");
		Team team2 = new Team(null,"pre-cert");
		
		User user = new User(null, "Cláudia", "claudia@hotmail.com", "123",team);
		User user2 = new User(null, "Eric", "erica@hotmail.com", "123",team);
		User user3 = new User(null, "camila", "camila@hotmail.com", "123",team2);
		
		Cellphone cellphone = new Cellphone(null,"cebu","pvt","CEBU-03","123456789","987456321");
		Cellphone cellphone2 = new Cellphone(null,"tahoe","evt","TAHOE-03","123456789","987456321");
		Cellphone cellphone3 = new Cellphone(null,"milan","evt","TAHOE-03","123456789","987456321");
		Cellphone cellphone4 = new Cellphone(null,"berlin","evt","TAHOE-03","123456789","987456321");
		Cellphone cellphone5 = new Cellphone(null,"racer-turbo","evt","TAHOE-03","123456789","987456321");
		Cellphone cellphone6 = new Cellphone(null,"cat2","evt","TAHOE-03","123456789","987456321");
		Cellphone cellphone7 = new Cellphone(null,"omde","evt","TAHOE-03","123456789","987456321");


		Request request = new Request(user, cellphone, PriorityEnum.RED.getCode(), LocalDateTime.now(), CategoryEnum.HEADSET.getCode(), null);
		Request request2 = new Request(user2, cellphone2,PriorityEnum.YELLOW.getCode(),LocalDateTime.now(),CategoryEnum.HEADSET.getCode(),null);
		Request request3 = new Request(user3, cellphone2,PriorityEnum.GREEN.getCode(),LocalDateTime.now(),CategoryEnum.HEADSET.getCode(),null);
		
		
		
		user2.getRequests().addAll(Arrays.asList(request2));
		user3.getRequests().addAll(Arrays.asList(request3));
		user.getRequests().addAll(Arrays.asList(request));
		
		Topic topic = new Topic(cellphone2, team2, LocalDateTime.now(), LocalDateTime.now().plusDays(3));
		Topic topic2 = new Topic(cellphone, team, LocalDateTime.now(), LocalDateTime.now().plusDays(3));
		
		cellphone.getTopics().addAll(Arrays.asList(topic2));
		cellphone2.getTopics().addAll(Arrays.asList(topic));
		
		team.getTopics().addAll(Arrays.asList(topic2));
		team2.getTopics().addAll(Arrays.asList(topic));
		
		
		teamRepository.saveAll(Arrays.asList(team,team2));
		userRepository.saveAll(Arrays.asList(user,user2,user3));
		cellphoneRepository.saveAll(Arrays.asList(cellphone,cellphone2,cellphone3,cellphone4,cellphone5,cellphone6,cellphone7));
		topicRepository.saveAll(Arrays.asList(topic,topic2));
		requestRepository.saveAll(Arrays.asList(request, request2, request3));

		
	}

}
