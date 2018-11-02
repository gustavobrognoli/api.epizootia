package com.epizootia.services;

import java.util.List;
import java.util.Optional;

import com.epizootia.entities.CorposAgua;

public interface CorposAguaService {

	Optional<CorposAgua> findById(int id);
	
	CorposAgua persistir(CorposAgua corposAgua);
	
	void apagar(CorposAgua corposAgua);
	
	List<CorposAgua> findAll();
}

