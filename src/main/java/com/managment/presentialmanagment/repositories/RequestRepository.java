package com.managment.presentialmanagment.repositories;

//import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.managment.presentialmanagment.domain.Request;
//import com.managment.presentialmanagment.domain.enums.CategoryEnum;

@Repository
public interface RequestRepository extends JpaRepository<Request, Integer>{
	//List<Request> findByCategory(CategoryEnum category);
}
