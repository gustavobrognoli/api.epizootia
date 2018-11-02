package com.epizootia.servicesImpl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.epizootia.entities.SituacaoFundiaria;
import com.epizootia.repositories.SituacaoFundiariaRepository;
import com.epizootia.services.SituacaoFundiariaService;

public class SituacaoFundiariaServiceImpl implements SituacaoFundiariaService {

	private static final Logger log = LoggerFactory.getLogger(SituacaoFundiariaServiceImpl.class);

	@Autowired
	private SituacaoFundiariaRepository repository;

	@Override
	public Optional<SituacaoFundiaria> findById(int id) {
		log.info("Buscando Situacao Fundiaria pelo código {}", id);
		Optional<SituacaoFundiaria> situacaoFundiaria = repository.findById(id);
		return situacaoFundiaria;
	}
	
	@Override
	public SituacaoFundiaria persistir(SituacaoFundiaria situacaoFundiaria) {
		log.info("Cadastrando SituacaoFundiaria: {}", situacaoFundiaria.toString());
		return this.repository.save(situacaoFundiaria);
	}

	@Override
	public void apagar(SituacaoFundiaria situacaoFundiaria) {
		log.info("Apagando Situacao Fundiaria: {}", situacaoFundiaria.toString());
		repository.delete(situacaoFundiaria);
	}

	@Override
	public List<SituacaoFundiaria> findAll() {
		return repository.findAll();
	}
}