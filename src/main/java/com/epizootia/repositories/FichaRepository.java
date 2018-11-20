package com.epizootia.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.epizootia.entities.Ficha;

@Transactional(readOnly = true)
public interface FichaRepository extends JpaRepository<Ficha, Integer>{

	@Override
	List<Ficha> findAll();
}
