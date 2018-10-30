package com.epizootia.servicesImpl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epizootia.entities.Viscera;
import com.epizootia.repositories.VisceraRepository;
import com.epizootia.services.VisceraService;

@Service
public class VisceraServiceImpl implements VisceraService {

	private static final Logger log = LoggerFactory.getLogger(VisceraServiceImpl.class);

	@Autowired
	private VisceraRepository repository;
	
	@Override
	public Optional<Viscera> findById(int id) {
		log.info("Buscando Viscera pelo código {}", id);
		Optional<Viscera> viscera = repository.findById(id);
		return viscera;
	}

	@Override
	public Viscera persistir(Viscera viscera) {
		log.info("Cadastrando Viscera: {}", viscera.toString());
		return this.repository.save(viscera);
	}

	@Override
	public void apagar(Viscera viscera) {
		log.info("Apagando Viscera: {}", viscera.toString());
		repository.delete(viscera);
	}

	@Override
	public List<Viscera> findAll() {
		return repository.findAll();
	}
}
