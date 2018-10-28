package com.epizootia.servicesImpl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epizootia.entities.Animal;
import com.epizootia.repositories.AnimalRepository;
import com.epizootia.services.AnimalService;

@Service
public class AnimalServiceImpl implements AnimalService {

	private static final Logger log = LoggerFactory.getLogger(AnimalServiceImpl.class);

	@Autowired
	private AnimalRepository repository;

	@Override
	public Optional<Animal> findById(int id) {
		log.info("Buscando Animal pelo código {}", id);
		Optional<Animal> animal = repository.findById(id);
		return animal;
	}
	
	@Override
	public Animal persistir(Animal animal) {
		log.info("Cadastrando Animal: {}", animal.toString());
		return this.repository.save(animal);
	}

	@Override
	public void apagar(Animal animal) {
		log.info("Apagando Animal: {}", animal.toString());
		repository.delete(animal);
	}

	@Override
	public List<Animal> findAll() {
		return repository.findAll();
	}

}
