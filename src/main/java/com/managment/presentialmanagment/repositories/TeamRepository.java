package com.managment.presentialmanagment.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.managment.presentialmanagment.domain.Team;

@Repository
public interface TeamRepository extends JpaRepository<Team, Integer>{
	
	@Transactional(readOnly=true)
	Team findByName(String name);
}
