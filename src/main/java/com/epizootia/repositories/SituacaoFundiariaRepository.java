package com.epizootia.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.epizootia.entities.SituacaoFundiaria;

@Transactional(readOnly = true)
public interface SituacaoFundiariaRepository extends JpaRepository<SituacaoFundiaria, Integer>{

	@Override
	List<SituacaoFundiaria> findAll();
}
