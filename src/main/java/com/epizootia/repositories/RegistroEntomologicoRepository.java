package com.epizootia.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.epizootia.entities.RegistroEntomologico;

public interface RegistroEntomologicoRepository extends JpaRepository<RegistroEntomologico, Integer>{

	@Override
	List<RegistroEntomologico> findAll();
}