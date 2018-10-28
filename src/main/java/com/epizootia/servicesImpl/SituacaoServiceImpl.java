package com.epizootia.servicesImpl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epizootia.entities.Situacao;
import com.epizootia.repositories.SituacaoRepository;
import com.epizootia.services.SituacaoService;

@Service
public class SituacaoServiceImpl implements SituacaoService{

	private static final Logger log = LoggerFactory.getLogger(SituacaoServiceImpl.class);

	@Autowired
	private SituacaoRepository repository;

	@Override
	public Optional<Situacao> findById(int id) {
		log.info("Buscando Situacaodo Animal pelo código {}", id);
		Optional<Situacao> situacao = repository.findById(id);
		return situacao;
	}

	@Override
	public Situacao persistir(Situacao situacao) {
		log.info("Cadastrando Situacao do Animal: {}", situacao.toString());
		return this.repository.save(situacao);
	}

	@Override
	public void apagar(Situacao situacao) {
		log.info("Apagando Situacao do Animal: {}", situacao.toString());
		repository.delete(situacao);
	}

	@Override
	public List<Situacao> findAll() {
		return repository.findAll();
	}
}
