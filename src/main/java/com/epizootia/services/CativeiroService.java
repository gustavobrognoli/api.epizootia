package com.epizootia.services;

import java.util.List;
import java.util.Optional;

import com.epizootia.entities.Cativeiro;

public interface CativeiroService {

	Optional<Cativeiro> findById(int id);
	
	Cativeiro persistir(Cativeiro cativeiro);
	
	void apagar(Cativeiro cativeiro);
	
	List<Cativeiro> findAll();
}
