package com.epizootia.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.epizootia.entities.UnidadeConservacao;

@Transactional(readOnly = true)
public interface UnidadeConservacaoRepository extends JpaRepository<UnidadeConservacao, Integer>{

	@Override
	List<UnidadeConservacao> findAll();
}
