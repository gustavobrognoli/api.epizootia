package com.epizootia.services;

import java.util.List;
import java.util.Optional;

import com.epizootia.entities.Idade;

public interface IdadeService {
	
	Optional<Idade> findById(int id);
	
	Idade persistir(Idade idade);
	
	void apagar(Idade idade);
	
	List<Idade> findAll();
}
