package com.managment.presentialmanagment.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.managment.presentialmanagment.domain.Client;
import com.managment.presentialmanagment.repositories.ClientRepository;
import com.managment.presentialmanagment.security.UserSS;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private ClientRepository repository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Client client = repository.findByEmail(email);
		
		if (client == null) {
			throw new UsernameNotFoundException(email);
		}
		return new UserSS(client.getId(),client.getEmail(),client.getPassword(), client.getProfiles());
	
	}

}
