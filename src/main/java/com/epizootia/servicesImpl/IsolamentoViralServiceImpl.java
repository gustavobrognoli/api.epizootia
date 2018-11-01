package com.epizootia.servicesImpl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epizootia.entities.IsolamentoViral;
import com.epizootia.repositories.IsolamentoViralRepository;
import com.epizootia.services.IsolamentoViralService;

@Service
public class IsolamentoViralServiceImpl implements IsolamentoViralService {

	private static final Logger log = LoggerFactory.getLogger(IsolamentoViralServiceImpl.class);

	@Autowired
	private IsolamentoViralRepository repository;

	@Override
	public Optional<IsolamentoViral> findById(int id) {
		log.info("Buscando Isolamento Viral pelo código {}", id);
		Optional<IsolamentoViral> isolamentoViral = repository.findById(id);
		return isolamentoViral;
	}

	@Override
	public IsolamentoViral persistir (IsolamentoViral isolamentoViral) {
		log.info("Cadastrando Isolamento Viral: {}", isolamentoViral.toString());
		return this.repository.save(isolamentoViral);
	}

	@Override
	public void apagar(IsolamentoViral isolamentoViral) {
		log.info("Apagando Isolamento Viral: {}", isolamentoViral.toString());
		repository.delete(isolamentoViral);
	}

	@Override
	public List<IsolamentoViral> findAll() {
		return repository.findAll();
	}

}
