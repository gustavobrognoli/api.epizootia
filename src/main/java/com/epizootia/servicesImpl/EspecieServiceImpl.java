package com.epizootia.servicesImpl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epizootia.entities.Especie;
import com.epizootia.repositories.EspecieRepository;
import com.epizootia.services.EspecieService;

@Service
public class EspecieServiceImpl implements EspecieService {

	private static final Logger log = LoggerFactory.getLogger(EspecieServiceImpl.class);

	@Autowired
	private EspecieRepository repository;

	@Override
	public Optional<Especie> findById(int id) {
		log.info("Buscando Especie do Animal pelo código {}", id);
		Optional<Especie> especie = repository.findById(id);
		return especie;
	}

	@Override
	public Especie persistir(Especie especie) {
		log.info("Cadastrando Especie do Animal: {}", especie.toString());
		return this.repository.save(especie);
	}

	@Override
	public void apagar(Especie especie) {
		log.info("Apagando Especie do Animal: {}", especie.toString());
		repository.delete(especie);
	}

	@Override
	public List<Especie> findAll() {
		return repository.findAll();
	}
}
