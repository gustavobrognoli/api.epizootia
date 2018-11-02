package com.epizootia.services;

import java.util.List;
import java.util.Optional;

import com.epizootia.entities.Caracteristicas;

public interface CaracteristicasService {

	Optional<Caracteristicas> findById(int id);
	
	Caracteristicas persistir(Caracteristicas caracteristicas);
	
	void apagar(Caracteristicas caracteristicas);
	
	List<Caracteristicas> findAll();
}
