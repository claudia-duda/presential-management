package com.managment.presentialmanagment.services;

import org.springframework.security.core.context.SecurityContextHolder;

import com.managment.presentialmanagment.security.UserSS;

public class UserService {
	
	public static UserSS authenticated() {
		try {
			return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();			
		}catch(Exception e) {
			return null;
		}
	}
}
