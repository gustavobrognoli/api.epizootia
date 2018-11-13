package com.epizootia.servicesImpl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epizootia.entities.Apreensao;
import com.epizootia.repositories.ApreensaoRepository;
import com.epizootia.services.ApreensaoService;

@Service
public class ApreensaoServiceImpl implements ApreensaoService{
	
	private static final Logger log = LoggerFactory.getLogger(ApreensaoServiceImpl.class);

	@Autowired
	private ApreensaoRepository repository;

	@Override
	public Optional<Apreensao> findById(int id) {
		log.info("Buscando Apreensão do Animal pelo código {}", id);
		Optional<Apreensao> apreensao = repository.findById(id);
		return apreensao;
	}

	@Override
	public Apreensao persistir(Apreensao apreensao) {
		log.info("Cadastrando Apreensão do Animal: {}", apreensao.toString());
		return this.repository.save(apreensao);
	}

	@Override
	public void apagar(Apreensao apreensao) {
		log.info("Apagando Apreensão do Animal: {}", apreensao.toString());
		repository.delete(apreensao);
	}

	@Override
	public List<Apreensao> findAll() {
		return repository.findAll();
	}
}

