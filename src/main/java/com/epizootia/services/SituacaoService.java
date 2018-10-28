package com.epizootia.services;

import java.util.List;
import java.util.Optional;

import com.epizootia.entities.Situacao;

public interface SituacaoService {

	Optional<Situacao> findById(int id);
	
	Situacao persistir(Situacao situacao);
	
	void apagar(Situacao situacao);
	
	List<Situacao> findAll();
}
