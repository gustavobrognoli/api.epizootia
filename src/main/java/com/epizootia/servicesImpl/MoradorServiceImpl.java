package com.epizootia.servicesImpl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epizootia.entities.Morador;
import com.epizootia.repositories.MoradorRepository;
import com.epizootia.services.MoradorService;

@Service
public class MoradorServiceImpl implements MoradorService {

	private static final Logger log = LoggerFactory.getLogger(MoradorServiceImpl.class);

	@Autowired
	private MoradorRepository repository;

	@Override
	public Optional<Morador> findById(int id) {
		log.info("Buscando Morador do Animal pelo código {}", id);
		Optional<Morador> morador = repository.findById(id);
		return morador;
	}

	@Override
	public Morador persistir(Morador morador) {
		log.info("Cadastrando Morador do Animal: {}", morador.toString());
		return this.repository.save(morador);
	}

	@Override
	public void apagar(Morador morador) {
		log.info("Apagando Morador do Animal: {}", morador.toString());
		repository.delete(morador);
	}

	@Override
	public List<Morador> findAll() {
		return repository.findAll();
	}
}