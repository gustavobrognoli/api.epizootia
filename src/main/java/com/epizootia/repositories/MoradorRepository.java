package com.epizootia.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.epizootia.entities.Morador;

@Transactional(readOnly = true)
public interface MoradorRepository extends JpaRepository<Morador, Integer>{

	@Override
	List<Morador> findAll();
}