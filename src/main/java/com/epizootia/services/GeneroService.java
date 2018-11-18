package com.epizootia.services;

import java.util.List;
import java.util.Optional;

import com.epizootia.entities.Genero;

public interface GeneroService {
	
	Optional<Genero> findById(int id);
	
	Genero persistir(Genero genero);
	
	void apagar(Genero genero);
	
	List<Genero> findAll();
}

