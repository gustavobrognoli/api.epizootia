package com.epizootia.services;

import java.util.List;
import java.util.Optional;

import com.epizootia.entities.Apreensao;

public interface ApreensaoService {

	Optional<Apreensao> findById(int id);
	
	Apreensao persistir(Apreensao apreensao);
	
	void apagar(Apreensao apreensao);
	
	List<Apreensao> findAll();
}
