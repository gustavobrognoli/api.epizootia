package com.epizootia.servicesImpl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epizootia.entities.Cativeiro;
import com.epizootia.repositories.CativeiroRepository;
import com.epizootia.services.CativeiroService;

@Service
public class CativeiroServiceImpl implements CativeiroService {

	private static final Logger log = LoggerFactory.getLogger(CativeiroServiceImpl.class);

	@Autowired
	private CativeiroRepository repository;

	@Override
	public Optional<Cativeiro> findById(int id) {
		log.info("Buscando Cativeiro do Animal pelo código {}", id);
		Optional<Cativeiro> cativeiro = repository.findById(id);
		return cativeiro;
	}

	@Override
	public Cativeiro persistir(Cativeiro cativeiro) {
		log.info("Cadastrando Cativeiro do Animal: {}", cativeiro.toString());
		return this.repository.save(cativeiro);
	}

	@Override
	public void apagar(Cativeiro cativeiro) {
		log.info("Apagando Cativeiro do Animal: {}", cativeiro.toString());
		repository.delete(cativeiro);
	}

	@Override
	public List<Cativeiro> findAll() {
		return repository.findAll();
	}
}
