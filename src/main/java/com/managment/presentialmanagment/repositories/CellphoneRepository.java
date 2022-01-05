package com.managment.presentialmanagment.repositories;

import java.util.List;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.managment.presentialmanagment.domain.Cellphone;
import com.managment.presentialmanagment.domain.Topic;

@Repository
public interface CellphoneRepository extends JpaRepository<Cellphone, Integer>{
	
	@Transactional(readOnly=true)
	Cellphone findByCode(String code);
	
	//Page<Cellphone> search(String name, List<Topic> topics, Pageable pageRequest);
}
