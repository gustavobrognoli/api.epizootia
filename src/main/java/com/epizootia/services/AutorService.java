package com.epizootia.services;

import java.util.List;
import java.util.Optional;

import com.epizootia.entities.Autor;

public interface AutorService {

	Optional<Autor> findByCodigo(String codigo);
	
	Optional<Autor> findById(String id);

	Autor persistir(Autor autor);

	void apagar(Autor autor);
	
	List<Autor> findAll();
	

}
