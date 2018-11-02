package com.epizootia.services;

import java.util.List;
import java.util.Optional;

import com.epizootia.entities.SituacaoFundiaria;

public interface SituacaoFundiariaService {

	Optional<SituacaoFundiaria> findById(int id);
	
	SituacaoFundiaria persistir(SituacaoFundiaria situacaoFundiaria);
	
	void apagar(SituacaoFundiaria situacaoFundiaria);
	
	List<SituacaoFundiaria> findAll();
}
