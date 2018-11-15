package com.epizootia.servicesImpl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epizootia.entities.Caracteristica;
import com.epizootia.repositories.CaracteristicaRepository;
import com.epizootia.services.CaracteristicaService;

@Service
public class CaracteristicaServiceImpl implements CaracteristicaService {

	private static final Logger log = LoggerFactory.getLogger(IsolamentoViralServiceImpl.class);

	@Autowired
	private CaracteristicaRepository repository;

	@Override
	public Optional<Caracteristica> findById(int id) {
		log.info("Buscando Caracteristica pelo código {}", id);
		Optional<Caracteristica> caracteristica = repository.findById(id);
		return caracteristica;
	}

	@Override
	public Caracteristica persistir (Caracteristica caracteristica) {
		log.info("Cadastrando Caracteristica: {}", caracteristica.toString());
		return this.repository.save(caracteristica);
	}

	@Override
	public void apagar(Caracteristica caracteristica) {
		log.info("Apagando Caracteristica: {}", caracteristica.toString());
		repository.delete(caracteristica);
	}

	@Override
	public List<Caracteristica> findAll() {
		return repository.findAll();
	}

}
