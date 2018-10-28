package com.epizootia.services;

import java.util.List;
import java.util.Optional;

import com.epizootia.entities.Especie;

public interface EspecieService {

	Optional<Especie> findById(int id);
	
	Especie persistir(Especie especie);
	
	void apagar(Especie especie);
	
	List<Especie> findAll();
}
