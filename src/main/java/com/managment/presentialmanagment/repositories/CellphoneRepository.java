package com.managment.presentialmanagment.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.managment.presentialmanagment.domain.Cellphone;

@Repository
public interface CellphoneRepository extends JpaRepository<Cellphone, Integer>{
	
	@Transactional(readOnly=true)
	Cellphone findByCode(String code);
	
	//Page<Cellphone> search(String name, List<Topic> topics, Pageable pageRequest);
}
