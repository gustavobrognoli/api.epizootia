package com.epizootia.services;

import java.util.List;
import java.util.Optional;

import com.epizootia.entities.UnidadeConservacao;

public interface UnidadeConservacaoService {

	Optional<UnidadeConservacao> findById(int id);
	
	UnidadeConservacao persistir(UnidadeConservacao unidadeConservacao);
	
	void apagar(UnidadeConservacao unidadeConservacao);
	
	List<UnidadeConservacao> findAll();
}
