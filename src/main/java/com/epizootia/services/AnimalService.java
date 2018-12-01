package com.epizootia.services;

import java.util.List;
import java.util.Optional;

import com.epizootia.entities.Animal;

public interface AnimalService {

	Optional<Animal> findById(int id);
	
	Animal persistir(Animal animal);
	
	void apagar(Animal animal);
	
	List<Animal> findAll();
	
	List<Animal> findAllByFicha(int id_Ficha);
}