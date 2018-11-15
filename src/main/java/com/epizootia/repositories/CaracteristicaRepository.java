package com.epizootia.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.epizootia.entities.Caracteristica;

public interface CaracteristicaRepository extends JpaRepository<Caracteristica, Integer>{
	
	@Override
	List<Caracteristica> findAll();
}
