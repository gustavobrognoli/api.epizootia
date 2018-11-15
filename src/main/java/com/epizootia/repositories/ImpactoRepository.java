package com.epizootia.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.epizootia.entities.Impacto;

@Transactional(readOnly = true)
public interface ImpactoRepository extends JpaRepository<Impacto, Integer>{

	@Override
	List<Impacto> findAll();
}
