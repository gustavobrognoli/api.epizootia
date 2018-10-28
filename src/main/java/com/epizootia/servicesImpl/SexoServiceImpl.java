package com.epizootia.servicesImpl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epizootia.entities.Sexo;
import com.epizootia.repositories.SexoRepository;
import com.epizootia.services.SexoService;

@Service
public class SexoServiceImpl implements SexoService {

	private static final Logger log = LoggerFactory.getLogger(SexoServiceImpl.class);

	@Autowired
	private SexoRepository repository;

	@Override
	public Optional<Sexo> findById(int id) {
		log.info("Buscando Sexo do Animal pelo código {}", id);
		Optional<Sexo> sexo = repository.findById(id);
		return sexo;
	}

	@Override
	public Sexo persistir(Sexo sexo) {
		log.info("Cadastrando Sexo do Animal: {}", sexo.toString());
		return this.repository.save(sexo);
	}

	@Override
	public void apagar(Sexo sexo) {
		log.info("Apagando Sexo do Animal: {}", sexo.toString());
		repository.delete(sexo);
	}

	@Override
	public List<Sexo> findAll() {
		return repository.findAll();
	}
}
