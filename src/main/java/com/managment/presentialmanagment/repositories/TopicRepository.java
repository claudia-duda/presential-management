package com.managment.presentialmanagment.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.managment.presentialmanagment.domain.Topic;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Integer>{

}
