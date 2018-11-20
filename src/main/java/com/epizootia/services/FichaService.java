package com.epizootia.services;

import java.util.List;
import java.util.Optional;

import com.epizootia.entities.Ficha;

public interface FichaService {

	Optional<Ficha> findById(int id);
	
	Ficha persistir(Ficha ficha);
	
	void apagar(Ficha ficha);
	
	List<Ficha> findAll();
	
	List<Ficha> findAllByClassificacao(int id_classificacaoFA);
}