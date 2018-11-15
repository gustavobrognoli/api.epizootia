package com.epizootia.services;

import java.util.List;
import java.util.Optional;

import com.epizootia.entities.Caracteristica;

public interface CaracteristicaService {

	Optional<Caracteristica> findById(int id);
	
	Caracteristica persistir(Caracteristica caracteristica);
	
	void apagar(Caracteristica caracteristica);
	
	List<Caracteristica> findAll();
}
