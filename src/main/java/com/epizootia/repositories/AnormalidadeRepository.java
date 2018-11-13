package com.epizootia.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.epizootia.entities.Anormalidade;

@Transactional(readOnly = true)
public interface AnormalidadeRepository extends JpaRepository<Anormalidade, Integer>{
	
	@Override
	List<Anormalidade> findAll();
	
}
