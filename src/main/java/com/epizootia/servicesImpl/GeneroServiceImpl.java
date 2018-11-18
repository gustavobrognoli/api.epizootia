package com.epizootia.servicesImpl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epizootia.entities.Genero;
import com.epizootia.repositories.GeneroRepository;
import com.epizootia.services.GeneroService;

@Service
public class GeneroServiceImpl implements GeneroService {

	private static final Logger log = LoggerFactory.getLogger(GeneroServiceImpl.class);

	@Autowired
	private GeneroRepository repository;

	@Override
	public Optional<Genero> findById(int id) {
		log.info("Buscando Genero do Animal pelo código {}", id);
		Optional<Genero> genero = repository.findById(id);
		return genero;
	}

	@Override
	public Genero persistir(Genero genero) {
		log.info("Cadastrando Genero do Animal: {}", genero.toString());
		return this.repository.save(genero);
	}

	@Override
	public void apagar(Genero genero) {
		log.info("Apagando Especie do Animal: {}", genero.toString());
		repository.delete(genero);
	}

	@Override
	public List<Genero> findAll() {
		return repository.findAll();
	}
}