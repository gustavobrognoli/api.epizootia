package com.epizootia.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.epizootia.entities.RecomendacaoVacinal;

@Transactional(readOnly = true)
public interface RecomendacaoVacinalRepository extends JpaRepository<RecomendacaoVacinal, Integer>{

	@Override
	List<RecomendacaoVacinal> findAll();
}

