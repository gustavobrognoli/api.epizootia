package com.epizootia.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.epizootia.entities.Animal;

@Transactional(readOnly = true)
public interface AnimalRepository extends JpaRepository<Animal, Integer>{

	@Override
	List<Animal> findAll();
}
