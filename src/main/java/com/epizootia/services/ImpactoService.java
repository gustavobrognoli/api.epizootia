package com.epizootia.services;

import java.util.List;
import java.util.Optional;

import com.epizootia.entities.Impacto;

public interface ImpactoService {

	Optional<Impacto> findById(int id);
	
	Impacto persistir(Impacto impacto);
	
	void apagar(Impacto impacto);
	
	List<Impacto> findAll();
}
