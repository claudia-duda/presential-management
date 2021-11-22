package com.managment.presentialmanagment.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.managment.presentialmanagment.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

}
