package com.managment.presentialmanagment.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.managment.presentialmanagment.domain.Team;
import com.managment.presentialmanagment.domain.Topic;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Integer>{

//	@Query("SELECT DISTINCT obj FROM Topic obj INNER JOIN obj.id.cellphone cellphone WHERE obj.id.cellphone.model LIKE %:model% AND cellphone IN :cellphones")
//	Page<Topic> search(@Param("model") String model, @Param("cellphones") List<Cellphone> cellphones, Pageable pageRequest);
	
	Page<Topic> findByTeam(Team team, Pageable pageRequest);

}
