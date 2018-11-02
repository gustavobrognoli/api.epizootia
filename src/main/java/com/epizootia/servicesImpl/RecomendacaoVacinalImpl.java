package com.epizootia.servicesImpl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epizootia.entities.RecomendacaoVacinal;
import com.epizootia.repositories.RecomendacaoVacinalRepository;
import com.epizootia.services.RecomendacaoVacinalService;

@Service
public class RecomendacaoVacinalImpl implements RecomendacaoVacinalService{

	private static final Logger log = LoggerFactory.getLogger(RecomendacaoVacinalImpl.class);

	@Autowired
	private RecomendacaoVacinalRepository repository;

	@Override
	public Optional<RecomendacaoVacinal> findById(int id) {
		log.info("Buscando Recomendacao Vacinal pelo código {}", id);
		Optional<RecomendacaoVacinal> recomendacaoVacinal = repository.findById(id);
		return recomendacaoVacinal;
	}

	@Override
	public RecomendacaoVacinal persistir(RecomendacaoVacinal recomendacaoVacinal) {
		log.info("Cadastrando Recomendacao Vacinal: {}", recomendacaoVacinal.toString());
		return this.repository.save(recomendacaoVacinal);
	}

	@Override
	public void apagar(RecomendacaoVacinal recomendacaoVacinal){
		log.info("Apagando Recomendacao Vacinal: {}", recomendacaoVacinal.toString());
		repository.delete(recomendacaoVacinal);
	}

	@Override
	public List<RecomendacaoVacinal> findAll() {
		return repository.findAll();
	}
}

