package com.managment.presentialmanagment.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.managment.presentialmanagment.domain.Cellphone;

@Repository
public interface CellphoneRepository extends JpaRepository<Cellphone, Integer>{

}
