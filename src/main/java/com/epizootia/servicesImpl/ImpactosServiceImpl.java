package com.epizootia.servicesImpl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epizootia.entities.Impactos;
import com.epizootia.repositories.ImpactosRepository;
import com.epizootia.services.ImpactosService;

@Service
public class ImpactosServiceImpl implements ImpactosService {

	private static final Logger log = LoggerFactory.getLogger(ImpactosServiceImpl.class);

	@Autowired
	private ImpactosRepository repository;

	@Override
	public Optional<Impactos> findById(int id) {
		log.info("Buscando Impactos pelo código {}", id);
		Optional<Impactos> impactos = repository.findById(id);
		return impactos;
	}
	
	@Override
	public Impactos persistir(Impactos impactos) {
		log.info("Cadastrando Impactos: {}", impactos.toString());
		return this.repository.save(impactos);
	}

	@Override
	public void apagar(Impactos impactos) {
		log.info("Apagando Impactos: {}", impactos.toString());
		repository.delete(impactos);
	}

	@Override
	public List<Impactos> findAll() {
		return repository.findAll();
	}
}
