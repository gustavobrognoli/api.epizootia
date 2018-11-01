package com.epizootia.services;

import java.util.List;
import java.util.Optional;

import com.epizootia.entities.IsolamentoViral;

public interface IsolamentoViralService {

	Optional<IsolamentoViral> findById(int id);
	
	IsolamentoViral persistir(IsolamentoViral isolamentoViral);
	
	void apagar(IsolamentoViral isolamentoViral);
	
	List<IsolamentoViral> findAll();
}
