package com.epizootia.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.epizootia.entities.Impactos;

@Transactional(readOnly = true)
public interface ImpactosRepository extends JpaRepository<Impactos, Integer>{

	@Override
	List<Impactos> findAll();
}
