package com.epizootia.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.epizootia.entities.Localidade;

@Transactional(readOnly = true)
public interface LocalidadeRepository extends JpaRepository<Localidade, Integer>{

	@Override
	List<Localidade> findAll();
}
