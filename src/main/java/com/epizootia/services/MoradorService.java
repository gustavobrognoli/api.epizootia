package com.epizootia.services;

import java.util.List;
import java.util.Optional;

import com.epizootia.entities.Morador;

public interface MoradorService {

	Optional<Morador> findById(int id);
	
	Optional<Morador> findTelefone(String telefone);
	
	Morador persistir(Morador morador);
	
	void apagar(Morador morador);
	
	List<Morador> findAll();
	
}
