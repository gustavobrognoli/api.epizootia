package com.epizootia.servicesImpl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epizootia.entities.Ficha;
import com.epizootia.repositories.FichaRepository;
import com.epizootia.services.FichaService;

@Service
public class FichaServiceImpl implements FichaService {

	private static final Logger log = LoggerFactory.getLogger(FichaServiceImpl.class);

	@Autowired
	private FichaRepository repository;

	@Override
	public Optional<Ficha> findById(int id) {
		log.info("Buscando Ficha pelo código {}", id);
		Optional<Ficha> ficha = repository.findById(id);
		return ficha;
	}
	
	@Override
	public Ficha persistir(Ficha ficha) {
		log.info("Cadastrando Ficha: {}", ficha.toString());
		return this.repository.save(ficha);
	}

	@Override
	public void apagar(Ficha ficha) {
		log.info("Apagando Ficha: {}", ficha.toString());
		repository.delete(ficha);
	}

	@Override
	public List<Ficha> findAll() {
		return repository.findAll();
	}

	@Override
	public List<Ficha> findAllByClassificacao(int id_classificacaoFA) {
		return repository.ListFichasByClassificacao(id_classificacaoFA);
	}
	
}
