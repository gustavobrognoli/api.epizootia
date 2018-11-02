package com.epizootia.services;

import java.util.List;
import java.util.Optional;

import com.epizootia.entities.MetodoCaptura;

public interface MetodoCapturaService {

	Optional<MetodoCaptura> findById(int id);
	
	MetodoCaptura persistir(MetodoCaptura metodoCaptura);
	
	void apagar(MetodoCaptura metodoCaptura);
	
	List<MetodoCaptura> findAll();
}
