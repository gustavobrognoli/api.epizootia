package com.epizootia.servicesImpl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epizootia.entities.Anormalidade;
import com.epizootia.repositories.AnormalidadeRepository;
import com.epizootia.services.AnormalidadeService;

@Service
public class AnormalidadeServiceImpl implements AnormalidadeService {

	private static final Logger log = LoggerFactory.getLogger(AnormalidadeServiceImpl.class);

	@Autowired
	private AnormalidadeRepository repository;

	@Override
	public Optional<Anormalidade> findById(int id) {
		log.info("Buscando Anormalidade pelo código {}", id);
		Optional<Anormalidade> anormalidade = repository.findById(id);
		return anormalidade;
	}

	@Override
	public Anormalidade persistir(Anormalidade anormalidade) {
		log.info("Cadastrando Anormalidade: {}", anormalidade.toString());
		return this.repository.save(anormalidade);
	}

	@Override
	public void apagar(Anormalidade anormalidade) {
		log.info("Apagando Anormalidade: {}", anormalidade.toString());
		repository.delete(anormalidade);
	}

	@Override
	public List<Anormalidade> findAll() {
		return repository.findAll();
	}

}
