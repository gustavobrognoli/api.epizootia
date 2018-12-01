package com.epizootia.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.epizootia.entities.Animal;

@Transactional(readOnly = true)
public interface AnimalRepository extends JpaRepository<Animal, Integer>{

	@Override
	List<Animal> findAll();
	
	@Query("SELECT a FROM Animal a WHERE a.ficha.id = ?1")
	List<Animal> findAllByFicha(int idFicha);
}
