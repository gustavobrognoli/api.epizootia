package com.epizootia.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.epizootia.entities.Cativeiro;

@Transactional(readOnly = true)
public interface CativeiroRepository extends JpaRepository<Cativeiro, Integer>{
	
	@Override
	List<Cativeiro> findAll();

}