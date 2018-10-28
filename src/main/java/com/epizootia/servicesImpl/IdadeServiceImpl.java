package com.epizootia.servicesImpl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epizootia.entities.Idade;
import com.epizootia.repositories.IdadeRepository;
import com.epizootia.services.IdadeService;

@Service
public class IdadeServiceImpl implements IdadeService {

	private static final Logger log = LoggerFactory.getLogger(IdadeServiceImpl.class);

	@Autowired
	private IdadeRepository repository;

	@Override
	public Optional<Idade> findById(int id) {
		log.info("Buscando Idade pelo codigo {}", id);
		Optional<Idade> idade = repository.findById(id);
		return idade;
	}

	@Override
	public Idade persistir(Idade idade) {
		log.info("Cadastrando idade: {}", idade.toString());
		return this.repository.save(idade);
	}

	@Override
	public void apagar(Idade idade) {
		log.info("Apagando idade: {}", idade.toString());
		repository.delete(idade);
	}

	@Override
	public List<Idade> findAll() {
		return repository.findAll();
	}

}
