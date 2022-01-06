package com.managment.presentialmanagment.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.managment.presentialmanagment.domain.Cellphone;
import com.managment.presentialmanagment.domain.Team;
import com.managment.presentialmanagment.domain.Topic;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Integer>{

	@Query("SELECT DISTINCT obj FROM Topic as obj "
			+ "INNER JOIN Cellphone as cell on obj.id.cellphone = cell "
			+ "WHERE cell.model LIKE %:model% AND cell IN :cellphones")
	Page<Topic> search(@Param("model") String model, @Param("cellphones") List<Cellphone> cellphones, Pageable pageRequest);
	
	
	Page<Topic> findByTeam(Team team, Pageable pageRequest);

}
