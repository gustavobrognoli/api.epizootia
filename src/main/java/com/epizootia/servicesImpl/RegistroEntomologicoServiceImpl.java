package com.epizootia.servicesImpl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epizootia.entities.RegistroEntomologico;
import com.epizootia.repositories.RegistroEntomologicoRepository;
import com.epizootia.services.RegistroEntomologicoService;

@Service
public class RegistroEntomologicoServiceImpl implements RegistroEntomologicoService{

	private static final Logger log = LoggerFactory.getLogger(RegistroEntomologicoServiceImpl.class);

	@Autowired
	private RegistroEntomologicoRepository repository;

	@Override
	public Optional<RegistroEntomologico> findById(int id) {
		log.info("Buscando RegistroEntomologico pelo código {}", id);
		Optional<RegistroEntomologico> registroEntomologico = repository.findById(id);
		return registroEntomologico;
	}
	
	@Override
	public RegistroEntomologico persistir(RegistroEntomologico registroEntomologico) {
		log.info("Cadastrando RegistroEntomologico: {}", registroEntomologico.toString());
		return this.repository.save(registroEntomologico);
	}

	@Override
	public void apagar(RegistroEntomologico registroEntomologico) {
		log.info("Apagando RegistroEntomologico: {}", registroEntomologico.toString());
		repository.delete(registroEntomologico);
	}

	@Override
	public List<RegistroEntomologico> findAll() {
		return repository.findAll();
	}

}

