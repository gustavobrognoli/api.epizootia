package com.epizootia.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.epizootia.entities.Caracteristicas;

public interface CaracteristicasRepository extends JpaRepository<Caracteristicas, Integer>{
	
	@Override
	List<Caracteristicas> findAll();
}
