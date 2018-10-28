package com.epizootia.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.epizootia.entities.Situacao;

@Transactional(readOnly = true)
public interface SituacaoRepository extends JpaRepository<Situacao, Integer>{
	
	@Override
	List<Situacao> findAll();

}
