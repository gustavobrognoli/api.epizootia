package com.epizootia.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.epizootia.entities.Genero;

@Transactional(readOnly = true)
public interface GeneroRepository extends JpaRepository<Genero, Integer>{

	@Override
	List<Genero> findAll();

}
