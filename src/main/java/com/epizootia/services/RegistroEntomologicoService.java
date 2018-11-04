package com.epizootia.services;

import java.util.List;
import java.util.Optional;

import com.epizootia.entities.RegistroEntomologico;

public interface RegistroEntomologicoService {

	Optional<RegistroEntomologico> findById(int id);
	
	RegistroEntomologico persistir(RegistroEntomologico registroEntomologico);
	
	void apagar(RegistroEntomologico registroEntomologico);
	
	List<RegistroEntomologico> findAll();
}