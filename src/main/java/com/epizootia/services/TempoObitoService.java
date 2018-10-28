package com.epizootia.services;

import java.util.List;
import java.util.Optional;

import com.epizootia.entities.TempoObito;

public interface TempoObitoService {

	Optional<TempoObito> findById(int id);
	
	TempoObito persistir(TempoObito tempoObito);
	
	void apagar(TempoObito tempoObito);
	
	List<TempoObito> findAll(); 
}
