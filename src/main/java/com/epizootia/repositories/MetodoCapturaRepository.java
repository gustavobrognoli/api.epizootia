package com.epizootia.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.epizootia.entities.MetodoCaptura;

@Transactional(readOnly = true)
public interface MetodoCapturaRepository extends JpaRepository<MetodoCaptura, Integer>{

	@Override
	List<MetodoCaptura> findAll();
}

