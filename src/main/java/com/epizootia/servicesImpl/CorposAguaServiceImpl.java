package com.epizootia.servicesImpl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epizootia.entities.CorposAgua;
import com.epizootia.repositories.CorposAguaRepository;
import com.epizootia.services.CorposAguaService;

@Service
public class CorposAguaServiceImpl implements CorposAguaService {

	private static final Logger log = LoggerFactory.getLogger(CorposAguaServiceImpl.class);

	@Autowired
	private CorposAguaRepository repository;

	@Override
	public Optional<CorposAgua> findById(int id) {
		log.info("Buscando Corpos d'Água pelo código {}", id);
		Optional<CorposAgua> corposAgua = repository.findById(id);
		return corposAgua;
	}
	
	@Override
	public CorposAgua persistir(CorposAgua corposAgua) {
		log.info("Cadastrando Corpos d'Água: {}", corposAgua.toString());
		return this.repository.save(corposAgua);
	}

	@Override
	public void apagar(CorposAgua corposAgua) {
		log.info("Apagando Corpos d'Água: {}", corposAgua.toString());
		repository.delete(corposAgua);
	}

	@Override
	public List<CorposAgua> findAll() {
		return repository.findAll();
	}
}