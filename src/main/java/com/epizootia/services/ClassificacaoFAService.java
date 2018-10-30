package com.epizootia.services;

import java.util.List;
import java.util.Optional;

import com.epizootia.entities.ClassificacaoFA;

public interface ClassificacaoFAService {

	Optional<ClassificacaoFA> findById(int id);
	
	ClassificacaoFA persistir(ClassificacaoFA classificacaoFA);
	
	void apagar(ClassificacaoFA classificacaoFA);
	
	List<ClassificacaoFA> findAll();
}