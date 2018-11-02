package com.epizootia.services;

import java.util.List;
import java.util.Optional;

import com.epizootia.entities.RecomendacaoVacinal;

public interface RecomendacaoVacinalService {

	Optional<RecomendacaoVacinal> findById(int id);
	
	RecomendacaoVacinal persistir(RecomendacaoVacinal recomendacaoVacinal);
	
	void apagar(RecomendacaoVacinal recomendacaoVacinal);
	
	List<RecomendacaoVacinal> findAll();
}

