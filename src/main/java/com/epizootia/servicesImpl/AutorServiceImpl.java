package com.epizootia.servicesImpl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epizootia.entities.Autor;
import com.epizootia.repositories.AutorRepository;
import com.epizootia.services.AutorService;

@Service
public class AutorServiceImpl implements AutorService {

	private static final Logger log = LoggerFactory.getLogger(AutorServiceImpl.class);

	@Autowired
	private AutorRepository repository;

	@Override
	public Optional<Autor> findByCodigo(String codigo) {
		log.info("Buscando Autor pelo codigo {}", codigo);
		return Optional.ofNullable(repository.findByCodigo(codigo));
	}

	@Override
	public Autor persistir(Autor autor) {
		log.info("Cadastrando autor: {}", autor.toString());
		return this.repository.save(autor);
	}

	@Override
	public List<Autor> findAll() {
		return  repository.findAll();
	}

	@Override
	public void apagar(Autor autor) {
		log.info("Apagando autor: {}", autor.toString());
		repository.delete(autor);
	}

	@Override
	public Optional<Autor> findById(String id) {
		log.info("Buscando Autor pelo codigo {}", id);
		Optional<Autor> autor = repository.findById(Integer.parseInt(id));
		return autor;
	}




}
