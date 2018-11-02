package com.epizootia.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.epizootia.entities.CorposAgua;

@Transactional(readOnly = true)
public interface CorposAguaRepository extends JpaRepository<CorposAgua, Integer>{

	@Override
	List<CorposAgua> findAll();
}
