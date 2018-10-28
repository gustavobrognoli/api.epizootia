package com.epizootia.services;

import java.util.List;
import java.util.Optional;

import com.epizootia.entities.Anormalidade;

public interface AnormalidadeService {

	Optional<Anormalidade> findById(int id);
	
	Anormalidade persistir(Anormalidade anormalidade);
	
	void apagar(Anormalidade anormalidade);
	
	List<Anormalidade> findAll();
}
