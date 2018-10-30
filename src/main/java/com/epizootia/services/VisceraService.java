package com.epizootia.services;

import java.util.List;
import java.util.Optional;

import com.epizootia.entities.Viscera;

public interface VisceraService {

	Optional<Viscera> findById(int id);
	
	Viscera persistir(Viscera viscera);
	
	void apagar(Viscera viscera);
	
	List<Viscera> findAll();
}
