package com.epizootia.servicesImpl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epizootia.entities.Caracteristicas;
import com.epizootia.repositories.CaracteristicasRepository;
import com.epizootia.services.CaracteristicasService;

@Service
public class CaracteristicasServiceImpl implements CaracteristicasService {

	private static final Logger log = LoggerFactory.getLogger(IsolamentoViralServiceImpl.class);

	@Autowired
	private CaracteristicasRepository repository;

	@Override
	public Optional<Caracteristicas> findById(int id) {
		log.info("Buscando Caracteristicas pelo código {}", id);
		Optional<Caracteristicas> caracteristicas = repository.findById(id);
		return caracteristicas;
	}

	@Override
	public Caracteristicas persistir (Caracteristicas caracteristicas) {
		log.info("Cadastrando Caracteristicas: {}", caracteristicas.toString());
		return this.repository.save(caracteristicas);
	}

	@Override
	public void apagar(Caracteristicas caracteristicas) {
		log.info("Apagando Caracteristicas: {}", caracteristicas.toString());
		repository.delete(caracteristicas);
	}

	@Override
	public List<Caracteristicas> findAll() {
		return repository.findAll();
	}

}
