package com.epizootia.servicesImpl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epizootia.entities.Localidade;
import com.epizootia.repositories.LocalidadeRepository;
import com.epizootia.services.LocalidadeService;

@Service
public class LocalidadeServiceImpl implements LocalidadeService {

	private static final Logger log = LoggerFactory.getLogger(LocalidadeServiceImpl.class);

	@Autowired
	private LocalidadeRepository repository;

	@Override
	public Optional<Localidade> findById(int id) {
		log.info("Buscando Localidade pelo código {}", id);
		Optional<Localidade> localidade = repository.findById(id);
		return localidade;
	}
	
	@Override
	public Localidade persistir(Localidade localidade) {
		log.info("Cadastrando Localidade: {}", localidade.toString());
		return this.repository.save(localidade);
	}

	@Override
	public void apagar(Localidade localidade) {
		log.info("Apagando Localidade: {}", localidade.toString());
		repository.delete(localidade);
	}

	@Override
	public List<Localidade> findAll() {
		return repository.findAll();
	}

}
