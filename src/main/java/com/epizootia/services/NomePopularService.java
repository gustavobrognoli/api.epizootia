package com.epizootia.services;

import java.util.List;
import java.util.Optional;

import com.epizootia.entities.NomePopular;

public interface NomePopularService {
	
	Optional<NomePopular> findById(int id);
	
	NomePopular persistir(NomePopular nomePopular);
	
	void apagar(NomePopular nomePopular);
	
	List<NomePopular> findAll();
}
