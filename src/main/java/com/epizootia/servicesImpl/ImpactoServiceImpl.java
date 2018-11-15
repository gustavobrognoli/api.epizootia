package com.epizootia.servicesImpl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epizootia.entities.Impacto;
import com.epizootia.repositories.ImpactoRepository;
import com.epizootia.services.ImpactoService;

@Service
public class ImpactoServiceImpl implements ImpactoService {

	private static final Logger log = LoggerFactory.getLogger(ImpactoServiceImpl.class);

	@Autowired
	private ImpactoRepository repository;

	@Override
	public Optional<Impacto> findById(int id) {
		log.info("Buscando Impactos pelo código {}", id);
		Optional<Impacto> impacto = repository.findById(id);
		return impacto;
	}
	
	@Override
	public Impacto persistir(Impacto impacto) {
		log.info("Cadastrando Impacto: {}", impacto.toString());
		return this.repository.save(impacto);
	}

	@Override
	public void apagar(Impacto impacto) {
		log.info("Apagando Impacto: {}", impacto.toString());
		repository.delete(impacto);
	}

	@Override
	public List<Impacto> findAll() {
		return repository.findAll();
	}
}
