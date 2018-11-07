package com.epizootia.services;

import java.util.List;
import java.util.Optional;

import com.epizootia.entities.Localidade;

public interface LocalidadeService {

	Optional<Localidade> findById(int id);
	
	Localidade persistir(Localidade localidade);
	
	void apagar(Localidade localidade);
	
	List<Localidade> findAll();
}
