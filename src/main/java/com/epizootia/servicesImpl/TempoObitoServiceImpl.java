package com.epizootia.servicesImpl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epizootia.entities.TempoObito;
import com.epizootia.repositories.TempoObitoRepository;
import com.epizootia.services.TempoObitoService;

@Service
public class TempoObitoServiceImpl implements TempoObitoService{
	
	private static final Logger log = LoggerFactory.getLogger(TempoObitoServiceImpl.class);
	
	@Autowired
	private TempoObitoRepository repository;
	
	@Override
	public Optional<TempoObito> findById(int id){
		log.info("Buscando Tempo do Óbito do Animal pelo código {}", id);
		Optional<TempoObito> tempoObito = repository.findById(id);
		return tempoObito;
	}

	@Override
	public TempoObito persistir(TempoObito tempoObito) {
		log.info("Cadastrando Tempo de Óbito do animal: {}", tempoObito.toString());
		return this.repository.save(tempoObito);
	}

	@Override
	public void apagar(TempoObito tempoObito) {
		log.info("Apagando Tempo de Óbito do animal: {}", tempoObito.toString());
		repository.delete(tempoObito);
	}

	@Override
	public List<TempoObito> findAll() {
		return repository.findAll();
	}

	
}
