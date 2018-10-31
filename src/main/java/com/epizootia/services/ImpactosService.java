package com.epizootia.services;

import java.util.List;
import java.util.Optional;

import com.epizootia.entities.Impactos;

public interface ImpactosService {

	Optional<Impactos> findById(int id);
	
	Impactos persistir(Impactos impactos);
	
	void apagar(Impactos impactos);
	
	List<Impactos> findAll();
}
