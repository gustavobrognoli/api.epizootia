package com.epizootia.servicesImpl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epizootia.entities.NomePopular;
import com.epizootia.repositories.NomePopularRepository;
import com.epizootia.services.NomePopularService;

@Service
public class NomePopularServiceImpl implements NomePopularService{

	private static final Logger log = LoggerFactory.getLogger(NomePopularServiceImpl.class);
	
	@Autowired
	private NomePopularRepository repository;
	
	@Override
	public Optional<NomePopular> findById(int id){
		log.info("Buscando Nome Popular do Animal pelo código {}", id);
		Optional<NomePopular> nomePopular = repository.findById(id);
		return nomePopular;
	}

	@Override
	public NomePopular persistir(NomePopular nomePopular) {
		log.info("Cadastrando Nome Popular do Animal: {}", nomePopular.toString());
		return this.repository.save(nomePopular);
	}

	@Override
	public void apagar(NomePopular nomePopular) {
		log.info("Apagando Nome Popular do Animal: {}", nomePopular.toString());
		repository.delete(nomePopular);
		
	}

	@Override
	public List<NomePopular> findAll() {
		return repository.findAll();
	}

	
}
