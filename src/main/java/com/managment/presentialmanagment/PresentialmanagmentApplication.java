package com.managment.presentialmanagment;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.managment.presentialmanagment.domain.User;
import com.managment.presentialmanagment.repositories.UserRepository;

@SpringBootApplication
public class PresentialmanagmentApplication implements CommandLineRunner{
	@Autowired
	private UserRepository userRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(PresentialmanagmentApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		User user = new User(null, "Cláudia", "claudia@hotmail.com", "123");
		User user2 = new User(null, "Eric", "erica@hotmail.com", "123");
		
		userRepository.saveAll(Arrays.asList(user,user2));
	}

}
