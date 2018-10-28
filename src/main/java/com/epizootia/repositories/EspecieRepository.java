package com.epizootia.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.epizootia.entities.Especie;

@Transactional(readOnly = true)
public interface EspecieRepository extends JpaRepository<Especie, Integer>{
	
	@Override
	List<Especie> findAll();

}
