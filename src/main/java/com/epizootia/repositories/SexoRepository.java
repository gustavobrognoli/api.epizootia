package com.epizootia.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.epizootia.entities.Sexo;

@Transactional(readOnly = true)
public interface SexoRepository extends JpaRepository<Sexo, Integer>{

	@Override
	List<Sexo> findAll();
}
